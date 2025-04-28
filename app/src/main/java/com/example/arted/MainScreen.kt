package com.example.arted

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.provider.MediaStore
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import java.io.IOException

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color,
    val strokeWidth: Float = 10f
)

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MainScreen(onNavigateToSubpage: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var currentColor by remember { mutableStateOf(Color.Black) }
    val lines = remember { mutableStateListOf<Line>() }
    var brushSize by remember { mutableFloatStateOf(10f) }
    var isEraser by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    // Permission Launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            Toast.makeText(context, "Storage permission is required to save drawings.", Toast.LENGTH_SHORT).show()
        }
    }

    // Request permission on launch
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    // Bitmap for saving
    val bitmap = remember {
        Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888)
    }
    val canvas = remember {
        Canvas(bitmap)
    }

    Column(Modifier.fillMaxSize()) {
        // Toolbar
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorPicker(onColorSelected = { selectedColor ->
                currentColor = selectedColor
                isEraser = false
            })
            BrushSizeSelector(
                currentSize = brushSize,
                onSizeSelected = { selectedSize -> brushSize = selectedSize },
                isEraser = isEraser,
                onEraserModeChanged = { eraserMode -> isEraser = eraserMode }
            )
            FloatingActionButton(onClick = { isExpanded = !isExpanded }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Expand Menu")
            }
        }
        if (isExpanded) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { isEraser = true }) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = "Eraser")
                }
                Button(onClick = { lines.clear() }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Reset")
                }
                Button(onClick = {
                    coroutineScope.launch {
                        saveDrawingToGallery(context, lines, bitmap, canvas)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Save, contentDescription = "Save")
                }
                Button(onClick = onNavigateToSubpage) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Subpage")
                }
            }
        }

        // Drawing Canvas
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            DrawingCanvas(
                lines = lines,
                brushSize = brushSize,
                isEraser = isEraser,
                currentColor = currentColor,
            )
        }
    }
}

@Composable
fun DrawingCanvas(
    lines: MutableList<Line>,
    brushSize: Float,
    isEraser: Boolean,
    currentColor: Color,
) {
    var currentLineColor by remember { mutableStateOf(currentColor) }
    var currentBrushSize by remember { mutableFloatStateOf(brushSize) }
    var currentIsEraser by remember { mutableStateOf(isEraser) }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                val line = Line(
                    start = change.position - dragAmount,
                    end = change.position,
                    color = if (currentIsEraser) Color.White else currentLineColor,
                    strokeWidth = currentBrushSize
                )
                lines.add(line)
            }
        }
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .drawBehind {
                    lines.forEach { line ->
                        drawLine(
                            color = line.color,
                            start = line.start,
                            end = line.end,
                            strokeWidth = line.strokeWidth,
                            cap = StrokeCap.Round
                        )
                    }
                }
        ) {
            currentLineColor = currentColor
            currentBrushSize = brushSize
            currentIsEraser = isEraser
        }
    }
}

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    val context = LocalContext.current
    val colorMap = mapOf(
        Color.Red to "Red",
        Color.Green to "Green",
        Color.Blue to "Blue",
        Color.Black to "Black"
    )
    Row {
        colorMap.forEach { (color, name) ->
            Box(
                Modifier
                    .size(40.dp)
                    .background(color, CircleShape)
                    .padding(4.dp)
                    .clickable {
                        onColorSelected(color)
                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}

@Composable
fun BrushSizeSelector(
    currentSize: Float,
    onSizeSelected: (Float) -> Unit,
    isEraser: Boolean,
    onEraserModeChanged: (Boolean) -> Unit
) {
    var sizeText by remember { mutableStateOf(currentSize.toString()) }

    Row {
        BasicTextField(
            value = sizeText,
            onValueChange = {
                sizeText = it
                val newSize = it.toFloatOrNull() ?: currentSize
                onSizeSelected(newSize)
            },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .width(60.dp)
                .background(Color.LightGray, CircleShape)
                .padding(8.dp)
        )
        Text(" px", Modifier.align(Alignment.CenterVertically))
    }
}

fun saveDrawingToGallery(
    context: Context,
    lines: List<Line>,
    bitmap: Bitmap,
    canvas: Canvas
) {
    if (lines.isEmpty()) {
        Toast.makeText(context, "Nothing to save.", Toast.LENGTH_SHORT).show()
        return
    }
    canvas.drawColor(android.graphics.Color.WHITE)

    val paint = android.graphics.Paint().apply {
        style = android.graphics.Paint.Style.STROKE
        strokeCap = android.graphics.Paint.Cap.ROUND
        strokeJoin = android.graphics.Paint.Join.ROUND
    }

    lines.forEach { line ->
        paint.color = line.color.toArgb()
        paint.strokeWidth = line.strokeWidth
        canvas.drawLine(line.start.x, line.start.y, line.end.x, line.end.y, paint)
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "drawing_${System.currentTimeMillis()}.png")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Arted")
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    try {
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                    throw IOException("Failed to compress bitmap")
                }
                Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Failed to save drawing", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        Toast.makeText(context, "Failed to save drawing: ${e.message}", Toast.LENGTH_SHORT).show()
        // Consider logging the exception for debugging purposes
        e.printStackTrace()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubpageScreen(onNavigateToMain: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manual") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToMain) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl("file:///android_asset/index.html")
                }
            }
        )
    }
}