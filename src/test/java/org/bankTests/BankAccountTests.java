package org.bankTests;

import org.bank.BankAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankAccountTests {

    @Test
    public void GetBalance_NewBankAccount_ReturnsStartingBalance(){
        // Arrange
        BankAccount account = new BankAccount(10);
        // Act
        // Assert
        assertEquals("Deberia ser igual a 10",10, account.getBalance());
    }

    @Test
    public void GetBalance_PossibleWithdraw_ReturnsBalanceMinusAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        account.withdraw(5);

        // Assert
        assertEquals("El balance deberia ser 5", 5, account.getBalance());
    }

}
