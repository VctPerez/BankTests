package org.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Victor Perez Armenta
 *
 * @implNote
 *  Para la implementación de los tests, tambien podría haber hecho use de la notación @Before...
 *  para la creacion del objeto BankAccount, pero he decidido dejarlo en los tests porque en algunos se me quedaba muy
 *  vacío el patrón AAA.
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

    @ParameterizedTest
    @CsvSource({
            "-10000, 0.001, 10",
            "10000, 0, 10",
            "10000, -10, 10",
            "10000, 0.001, 0",
            "10000, 0.001, -10"
    })
    public void Payment_InvalidParameters_ThrowsIllegalArgumentException(double total_amount, double interest, int npayments){
        //Arrange
        BankAccount account = new BankAccount(10);
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {account.payment(total_amount, interest, npayments);},
                    "Deberia lanzar una excepcion del tipo 'IllegalArgumentException'");
    }

    @Test
    @DisplayName("Payment of amount 1000, with interest 0.01, 5 months, in month 2 must be: 605,96")
    public void Payment_ValidParameters_Returns206Aprox(){
        // Arrange
        BankAccount account = new BankAccount(10);
        double total_amount = 1000, interest = 0.01, payment;
        int months = 5;

        // Act
        payment = account.payment(total_amount, interest, months);

        // Assert
        assertEquals(206.03, payment, 0.01,"PendingAmount must be 605,960003.");

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
    @DisplayName("Pending payment of amount 1000, with interest 0.01, 5 months, in month 2 must be: 605,96")
    public void Pending_ValidParameters_Returns606Aprox(){
        // Arrange
        BankAccount account = new BankAccount(10);
        double total_amount = 1000, interest = 0.01, pendingAmount;
        int months = 5;

        // Act
        pendingAmount = account.pending(total_amount, interest, months, 2);

        // Assert
        assertEquals(605.96003, pendingAmount, 0.01,"PendingAmount must be 605,960003.");
    }

    @Test
    public void Pending_WhenMonthGreaterThanNpayments_ThrowsIllegalArgumentException(){
        // Arrange
        BankAccount account = new BankAccount(10);

        // Act
        // Assert - No he parametrizado el test ya que solo son dos casos a comprobar.
        assertThrows(IllegalArgumentException.class, () -> account.pending(10000, 0.001, 10, -1));
        assertThrows(IllegalArgumentException.class, () -> account.pending(10000, 0.001, 10, 15));
    }

}
