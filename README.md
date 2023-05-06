# IF2212-Tugas-Besar-OOP

## Anggota Kelompok 5

| No. | NIM | Nama |
|-----|-----|------|
| 1 | 18221051 | Muhammad Shulhan |
| 2 | 18221057 | Danang Ihsan |
| 3 | 18221069 | Gibran Fasha Ghazanfar |
| 4 | 18221099 | Clara Alrosa Fernanda Sinaga |
| 5 | 18221123 | Abraham Megantoro Samudra |

## Cara Menjalankan Program di Local
1. Clone Repository

```
git clone https://github.com/abrahammegantoro/IF2212-Tugas-Besar-OOP.git
```

2. Pindah ke folder clone

```
cd IF2212-Tugas-Besar-OOP
```

3. Buka Command Prompt dan jalankan perintah berikut

```
javac -cp gson-2.10.1.jar -encoding UTF-8 -d bin src/AllData/*.java src/Inventory/Inventory.java src/Item/BahanBaku/*.java src/Item/Furniture/*.java src/Item/Furniture/Bed/*.java src/Item/Furniture/Stove/*.java src/Item/Masakan/*.java src/Item/*.java src/MainMenu/*.java src/Pekerjaan/Pekerjaan.java src/Ruangan/Ruangan.java src/Rumah/Rumah.java src/Sim/Sim.java src/World/*.java src/Main.java
```

4. Setelah itu, Jalankan program

```
java -cp bin;gson-2.10.1.jar src/Main
```

5. Selamat Bermain :)
