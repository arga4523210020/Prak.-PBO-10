package com.atm.transaction;

import com.atm.model.Account;
import java.util.Scanner;

public class Withdrawal extends Transaction {

    public Withdrawal(Account account) {
        super(account);
    }

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
}
