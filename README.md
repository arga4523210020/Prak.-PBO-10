# 4523210020_Arga Bona Simarmata

# Implementasi Fitur Baru pada Sistem ATM

## 1. Fitur Ubah PIN

### a. Tambahkan opsi “Ubah PIN” pada menu utama
#### ![image](https://github.com/user-attachments/assets/04a6c6c0-6525-40ff-b20c-57628065c863)
```java
// ATM.java (showMenu method)
case 6:
    scanner.nextLine(); // Membersihkan buffer
    System.out.print("Masukkan PIN lama: ");
    String oldPin = scanner.nextLine();
    System.out.print("Masukkan PIN baru: ");
    String newPin = scanner.nextLine();
    System.out.print("Konfirmasi PIN baru: ");
    String confirmNewPin = scanner.nextLine();

    if (account.changePin(oldPin, newPin, confirmNewPin)) {
        System.out.println("PIN berhasil diubah.");
    } else {
        System.out.println("Gagal mengubah PIN.");
    }
    break;
```
### b. Buat metode dalam kelas Account untuk mengubah PIN, yaitu: changePin()
```java
// Account.java
public boolean changePin(String oldPin, String newPin, String confirmNewPin) {
    if (!this.pin.equals(oldPin)) {
        System.out.println("PIN lama salah.");
        return false;
    }
    if (!newPin.equals(confirmNewPin)) {
        System.out.println("PIN baru tidak cocok.");
        return false;
    }
    this.pin = newPin;
    System.out.println("PIN berhasil diperbarui.");
    return true;
}
```
### c. Dalam method tersebut lakukan hal berikut:
#### i.	Verifikasi PIN lama
##### ![image](https://github.com/user-attachments/assets/c972e68e-2a13-4abf-b370-343e3a4c774d)
```java
if (!this.pin.equals(oldPin)) {
    System.out.println("PIN lama salah.");
    return false;
}
```
#### ii.	Minta nasabah memasukkan PIN baru dua kali
##### ![image](https://github.com/user-attachments/assets/60b219e1-caaf-4d3d-904d-96f4cce6dd25)
```java
System.out.print("Masukkan PIN baru: ");
String newPin = scanner.nextLine();
System.out.print("Konfirmasi PIN baru: ");
String confirmNewPin = scanner.nextLine();
```
#### iii.	Validasi bahwa kedua input PIN baru cocok
##### ![image](https://github.com/user-attachments/assets/42126ddb-bc30-40fb-9fad-f8111cca4abc)
```java
if (!newPin.equals(confirmNewPin)) {
    System.out.println("PIN baru tidak cocok.");
    return false;
}
```
#### iv.	Perbarui PIN jika validasi berhasil
##### ![image](https://github.com/user-attachments/assets/4d1bc9c6-ae18-4c61-9771-b8fedc238ab2)
```java
this.pin = newPin;
System.out.println("PIN berhasil diperbarui.");
return true;
```
## 2.	Validasi Saldo Minimal pada saat penarikan
### ![image](https://github.com/user-attachments/assets/18cbf0c6-2604-45cd-87d7-4eeb5f47694c)
### a.	Modifikasi fitur penarikan sehingga nasabah harus menyisakan saldo minimal setelah penarikan dilakukan. Misal, saldo minial adalah Rp50,000-
```java
// Account.java
public static final double MINIMUM_BALANCE = 50000.0;

public boolean isSufficientBalance(double withdrawalAmount) {
    return (this.balance - withdrawalAmount) >= MINIMUM_BALANCE;
}
```
### b.	Langkah-langkah:
#### i.	Tentukan saldo minimal, tambahkan konstanta MINIMUM_BALANCE dalam kelas Account
```java
// Withdrawal.java
@Override
public void execute() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Masukkan jumlah penarikan: ");
    double amount = scanner.nextDouble();

    if (amount <= 0) {
        System.out.println("Jumlah penarikan tidak valid.");
        return;
    }

    if (account.isSufficientBalance(amount)) {
        account.debit(amount);
        System.out.println("Penarikan berhasil. Saldo Anda sekarang: " + account.getBalance());
    } else {
        System.out.println("Saldo tidak mencukupi. Anda harus menyisakan saldo minimal sebesar Rp" + Account.MINIMUM_BALANCE);
    }
}
```
#### ii.	Modifikasi methode execute() dalam kelas Withdrawal untuk memeriksa apakah saldo setelah penarikan tidak kuran dari saldo minimal
```java
if (account.isSufficientBalance(amount)) {
    account.debit(amount);
    System.out.println("Penarikan berhasil. Saldo Anda sekarang: " + account.getBalance());
} else {
    System.out.println("Saldo tidak mencukupi. Anda harus menyisakan saldo minimal sebesar Rp" + Account.MINIMUM_BALANCE);
}
```
#### iii.	Jika saldo tidak mencukupi, tampilkan pesan kesalahan
```java
if (!account.isSufficientBalance(amount)) {
    System.out.println("Saldo tidak mencukupi. Anda harus menyisakan saldo minimal sebesar Rp" + Account.MINIMUM_BALANCE);
}
```
