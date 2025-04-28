# Arted
Arted adalah aplikasi Android yang dirancang untuk memberikan pengalaman menggambar digital yang sederhana dan intuitif. Aplikasi ini memungkinkan pengguna untuk membuat sketsa, menggambar, dan berkreasi dengan berbagai pilihan warna dan ukuran kuas. Tujuan utama dari program ini adalah untuk menyediakan platform yang mudah digunakan bagi siapa saja yang ingin mengekspresikan kreativitas mereka melalui gambar, baik untuk sekadar mencoret-coret, membuat sketsa cepat, atau membuat karya seni yang lebih detail.

Fungsi utama dari Arted meliputi kemampuan untuk menggambar dengan berbagai warna, menyesuaikan ukuran kuas, menghapus bagian gambar, menyimpan gambar ke galeri perangkat, dan mereset kanvas untuk memulai gambar baru. Aplikasi ini juga menyediakan navigasi ke halaman lain, yang memungkinkan pengguna untuk memperluas pengalaman mereka. Dengan antarmuka yang bersih dan mudah dipahami, Arted cocok untuk pengguna dari segala usia dan tingkat keahlian.

Key Features: Pemilihan warna, Penyesuaian ukuran kuas, Alat penghapus, reset canvas, save to gallery.

# 1. Fitur Utama

•	Menggambar Bebas: Pengguna dapat menggambar di kanvas dengan gerakan bebas.

•	Pemilihan Warna: Pengguna dapat memilih warna yang diinginkan dari palet warna yang tersedia (Merah, Hijau, Biru, Hitam).

•	Pengaturan Ukuran Kuas: Pengguna dapat menyesuaikan ukuran kuas sesuai kebutuhan, dengan input numerik.

•	Penghapus (Eraser): Pengguna dapat menghapus bagian gambar dengan menggunakan fitur penghapus.

•	Reset Kanvas: Pengguna dapat menghapus seluruh gambar di kanvas dan memulai gambar baru.

•	Simpan Gambar: Pengguna dapat menyimpan gambar yang telah dibuat ke galeri perangkat.

•	Navigasi ke Subpage: Pengguna dapat berpindah ke halaman lain.

•	Navigasi ke Main: Pengguna dapat kembali ke halaman utama.

•	Navigasi ke Opening: Pengguna dapat berpindah ke halaman pembuka.

Hal yang dipertimbagnkan:

•	Alat menggambar Tingkat lanjut (perubahan bentuk kuas, layer, gradient, dll).

•	Fitur Cloud storage atau sharing.

•	Pemilihan warna kustom (pemilihan melebihi warna yang ditentukan).

•	Fungsi undo/redo.

# 2. Proses Pembuatan
2.1.	Planning dan Requirements:
Konsep awal: Project ini berawal dengan ide pengembangan aplikasi menggambar simpel.

Requirements:

•	Canvas untuk menggambar.

•	Pemilihan warna.

•	Penyesuaian ukuran kuas.

•	Penghapus.

•	Canvas reset.

•	Fitur penyimpanan.

Metodologi: Pendekatan iteratif digunakan, dengan fokus pada membangun fungsionalitas inti terlebih dahulu dan kemudian menyempurnakannya.

2.2.	Fase Design

•	UI Design: User Interface(UI) di desain intuitif dan rapi, dengan toolbar untuk kontrol alat menggambar dan canvas untuk menggambar.

•	Component-Based: UI disusun menggunakan Jetpack Compose, memecah UI menjadi komponen-komponen yang dapat digunakan kembali seperti ColorPicker dan BrushSizeSelector.

•	State Management: Sistem manajemen status Compose digunakan untuk menangani status gambar (garis, warna, ukuran kuas).

2.3.	Fase Pengembangan

Teknologi yang digunakan sebagai berikut

Bahasa: Kotlin

Framework: Jetpack Compose (untuk UI), Android Jetpack (untuk komponen lainnya)

Tools: Android Studio

Database: Tidak ada database yang digunakan (gambar disimpan langsung ke galeri perangkat).

2.4.	Testing dan kontrol kualitas

•	Pengujian Manual: Aplikasi diuji secara manual pada emulator dan perangkat fisik untuk memastikan fungsionalitas gambar, pemilihan warna, penyesuaian ukuran kuas, penghapus, pengaturan ulang, dan penyimpanan berfungsi seperti yang diharapkan.

•	Fokus: Fokus utamanya adalah memastikan fungsionalitas menggambar berjalan lancar dan responsif.

•	Pelacakan Masalah: Masalah apa pun yang ditemukan selama pengujian ditangani secara langsung dalam kode.

2.5.	Deployment

•	Target: Aplikasi ini didesain untuk digunakan pada perangkat Android.

•	Process: Aplikasi ini dapat dibuat dan diinstal langsung dari Android Studio ke perangkat atau emulator.

# 3.	Hasil Project
3.1.	Fitur utama, tampilan aplikasi dan fungsionalitas:

•	Tampilan layer loading setika pertama kali masuk aplikasi yang menampilkan logo aplikasi dengan background berwarna putih 

![Image](https://github.com/user-attachments/assets/6c87d780-075e-45d8-95fa-b1f980fe8fa4)

•	Halaman Pembuka setelah layar loading telah selesai. Berisi kalimat selamat datang dan tombol get started untuk memulai aplikasi

![Image](https://github.com/user-attachments/assets/44596382-a05f-4b3d-b52e-67ad548ca6a0)
 
•	Halaman Subpage setelah menekan tombol subpage pada halaman utama, isinya berupa manual penggunaan aplikasi Arted

![Image](https://github.com/user-attachments/assets/0afeccce-3fc0-4c81-9f1f-405bb3626678)
 
•	Kanvas : Area kanvas putih yang besar di mana pengguna dapat menggambar dengan bebas menggunakan input sentuh.

 ![Image](https://github.com/user-attachments/assets/dc83bae5-6682-4905-b635-74657f866aa2)
 ![Image](https://github.com/user-attachments/assets/00908960-2074-48ea-91cf-8b4c57235263)
 
•	Pemilih Warna: Komponen ColorPicker memungkinkan pengguna untuk memilih dari empat warna yang sudah ditentukan sebelumnya (Merah, Hijau, Biru, Hitam). 

![Image](https://github.com/user-attachments/assets/91adca5c-7b07-4096-a9ff-ebdf6227f82c)

•	Pemilih Ukuran Kuas: Komponen BrushSizeSelector memungkinkan pengguna untuk menyesuaikan ukuran kuas dengan memasukkan nilai numerik.  

![Image](https://github.com/user-attachments/assets/0f47471b-e495-48d5-bed6-5ec2a75a93db)

•	Drop down: dikarenakan banyaknya tombol pada top app bar, pada layer yang lebih kecil akan menghasilkan tampilan yang menumpuk jadi fungsinya tidak bisa dipakai oleh karena itu saya memakai drop down button dan penggunaan icon untuk setiap fungsi agar cukup. 

  ![Image](https://github.com/user-attachments/assets/14bff901-4e90-4416-9625-6146de0d7296)
  ![Image](https://github.com/user-attachments/assets/2a836fc5-fa89-4a00-be52-503a129a6ace)

•	Penghapus: Alat penghapus yang memungkinkan pengguna untuk menghapus bagian dari gambar mereka dengan menggambar di atasnya dengan warna putih. 

![Image](https://github.com/user-attachments/assets/9d021127-1622-4981-9685-3342f3703863)
 
•	Reset: Tombol “Reset” akan mengosongkan kanvas, menghapus semua garis. 

![Image](https://github.com/user-attachments/assets/6bc2079a-aeb1-4ed9-bef2-a460b3d108ba)
 
•	Simpan: Tombol “Save” menyimpan gambar saat ini ke galeri perangkat sebagai gambar PNG. 

![Image](https://github.com/user-attachments/assets/1f9f5bef-599b-4052-a08b-acb3e52601d7)
 
•	Tombol Subpage untuk membuka halaman Subpage

![Image](https://github.com/user-attachments/assets/3efbb9d8-6e58-4abb-acc9-431e073fcc41)
 
•	Izin: Aplikasi meminta izin penyimpanan untuk menyimpan gambar.


# 4.	Penutup
Pengembangan aplikasi Arted memberikan pengalaman yang berharga dalam membangun aplikasi Android menggunakan Kotlin dan Jetpack Compose. Tantangan utama yang dihadapi adalah mengelola state dan interaksi pengguna dengan kanvas gambar, terutama dalam menangani input sentuhan dan memastikan bahwa area gambar tidak tumpang tindih dengan elemen UI lainnya. Selain itu, memahami cara kerja pointerInput, detectDragGestures, dan Canvas di Compose membutuhkan pemahaman yang mendalam.

Melalui proses ini, saya belajar banyak tentang:

•	Manajemen State di Compose: Memahami bagaimana cara mengelola state dengan benar dan memastikan bahwa perubahan state tercermin di UI.

•	Penanganan Input Pengguna: Mempelajari cara menangani input sentuhan dengan pointerInput dan detectDragGestures, serta bagaimana membatasi area input agar tidak mengganggu elemen UI lainnya.

•	Layout di Compose: Memahami cara menggunakan Column, Row, Box, dan weight untuk membuat layout yang responsif dan tidak tumpang tindih.

•	Menggambar dengan Canvas: Mempelajari cara menggunakan Canvas untuk menggambar bentuk dan garis, serta bagaimana mengintegrasikannya dengan input pengguna.

•	Penyimpanan Data: Memahami cara menyimpan gambar ke galeri perangkat menggunakan MediaStore.

•	Navigasi: Memahami cara menggunakan NavHost untuk berpindah antar halaman.

Selain itu, saya juga belajar tentang pentingnya iterasi dan pengujian dalam pengembangan aplikasi. Setiap kali ada masalah, saya harus kembali ke kode, menganalisis masalah, dan mencari solusi yang tepat. Proses ini membantu saya untuk lebih memahami bagaimana setiap komponen bekerja dan bagaimana mereka berinteraksi satu sama lain.
Meskipun Arted masih merupakan aplikasi yang sederhana, proses pengembangannya telah memberikan saya dasar yang kuat dalam pengembangan aplikasi Android modern. Saya berharap dapat terus mengembangkan aplikasi ini dan menambahkan fitur-fitur baru di masa mendatang. Saya juga berharap dapat menerapkan pengetahuan yang saya peroleh untuk proyek-proyek yang lebih kompleks.


# 5. Referensi
• https://www.youtube.com/watch?v=nMeO3XxjfBs

• https://developer.android.com/jetpack/compose

• https://developer.android.com/jetpack/compose/navigation

• https://developer.android.com/kotlin/ktx

• https://developer.android.com/reference/androidx/activity/compose/package-summary

• https://developer.android.com/topic/libraries/architecture/lifecycle

• https://developer.android.com/jetpack/compose/designsystems/material3

• https://developer.android.com/reference/androidx/core/splashscreen/package-summary

• https://developer.android.com/training/data-storage/shared/media

• https://developer.android.com/develop/ui/views/layout/webapps/webview
