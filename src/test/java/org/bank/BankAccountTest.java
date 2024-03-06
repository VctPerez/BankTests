package org.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Victor Perez Armenta
 *
 * @
 */
public class BankAccountTest {
    @Test
    public void GetBalance_NewBankAccount_ReturnsStartingBalance(){
        // Arrange
        BankAccount account = new BankAccount(10);
        // Act
        // Assert
        assertEquals(10, account.getBalance(), "Deberia ser igual a 10");
    }

    @Test
    public void GetBalance_PossibleWithdraw_ReturnsTrueAndBalanceMinusAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        account.withdraw(5);

        // Assert
        assertEquals( 5, account.getBalance(), "El balance deberia ser 5");
    }

    @Test
    public void GetBalance_CorrectDeposit_ReturnsBalancePlusAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);
        int newBalance;

        // Act
        newBalance = account.deposit(10);

        // Assert
        assertEquals(newBalance, account.getBalance(),"El balance deberia ser 20");
    }


    @Test
    public void Withdraw_AmountBiggerThanBalance_ReturnsFalseAndSameBalance(){
        // Arrange
        BankAccount account = new BankAccount(10);
        boolean completed;
        // Act
        completed = account.withdraw(20);

        // Assert
        assertFalse(completed, "Withdraw deberia retornar False");
        assertEquals(10, account.getBalance(), "GetBalance no deberia haber cambiado");
    }

    @Test
    public void Deposit_NegativeAmount_ThrowsIllegalArgumentException(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {account.deposit(-10);},
                "Deberia lanzar una excepcion del tipo 'IllegalArgumentException'");
    }

    @Test
    public void Pending_MonthIs0_ReturnsAmount(){
        // Arrange
        BankAccount account = new BankAccount(10);
        double pendingAmount, amount = 100;

        // Act
        pendingAmount = account.pending(amount, 2, 5, 0);

        // Assert
        assertEquals(amount, pendingAmount, 0.0001, "Amount y pendingAmount deberian ser iguales");
    }

    @Test
    @DisplayName("Pending payment of amount 1000, with interes 0.01, 5 months, in month 2 must be: 605,96")
    public void Pending_ValidParameters_Returns606Aprox(){
        // Arrange
        BankAccount account = new BankAccount(10);
        double total_amount = 1000, interes = 0.01, pendingAmount;
        int months = 5;

        // Act
        pendingAmount = account.pending(total_amount, interes, months, 2);

        // Assert
        assertEquals(605.96003, pendingAmount, 0.01,"PendingAmount must be 605,960003.");
    }

}
