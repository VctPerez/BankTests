package org.bank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankAccountTest {

    @Test
    public void GetBalance_NewBankAccount_ReturnsStartingBalance(){
        // Arrange
        BankAccount account = new BankAccount(10);
        // Act
        // Assert
        assertEquals("Deberia ser igual a 10",10, account.getBalance());
    }

    @Test
    public void GetBalance_PossibleWithdraw_ReturnsTrueAndBalanceMinusAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        account.withdraw(5);

        // Assert
        assertEquals("El balance deberia ser 5", 5, account.getBalance());
    }

    @Test
    public void GetBalance_CorrectDeposit_ReturnsBalancePlusAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        account.deposit(10);

        // Assert
        assertEquals("El balance deberia ser 20", 20, account.getBalance());
    }


    @Test
    public void Withdraw_AmountBiggerThanBalance_ReturnsFalseAndSameBalance(){
        // Arrange
        BankAccount account = new BankAccount(10);
        boolean completed;
        // Act
        completed = account.withdraw(20);

        // Assert
        assertFalse("Withdraw deberia retornar False", completed);
        assertEquals("GetBalance no deberia haber cambiado", 10, account.getBalance());
    }
}
