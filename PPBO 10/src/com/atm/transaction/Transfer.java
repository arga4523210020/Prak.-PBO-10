package com.atm.transaction;

import com.atm.model.Account;
import java.util.Scanner;

public class Transfer extends Transaction {
    private Account targetAccount;

    public Transfer(Account account, Account targetAccount) {
        super(account);
        this.targetAccount = targetAccount;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan jumlah transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Jumlah transfer tidak valid.");
            return;
        }

        if (account.isSufficientBalance(amount)) {
            account.debit(amount);
            targetAccount.credit(amount);
            System.out.println("Transfer berhasil. Saldo Anda sekarang: " + account.getBalance());
        } else {
            System.out.println("Saldo tidak mencukupi. Anda harus menyisakan saldo minimal sebesar Rp" + Account.MINIMUM_BALANCE);
        }
    }
}
