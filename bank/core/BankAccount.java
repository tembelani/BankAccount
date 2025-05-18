package bank.core;
    
import java.util.Objects;


public class BankAccount {
    private double balance;
    private final String accountNumber;
    private final String accountHolder;
    private static int accountCount = 100;

    //Constructors
    public BankAccount(String accountHolder, double initialDeposit){
        if(initialDeposit <= 0) {
            throw new IllegalArgumentException("Initial deposit must be greater than zero.");
        }   
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder cannot be null"); // Ensure account holder is not null
        this.balance = initialDeposit; // Set initial balance
        this.accountNumber = "CP-" + (accountCount++); // Generate a unique account number
    }

    public BankAccount(String accountHolder) {
        this(accountHolder, 0.0); // Default constructor with default values
    }

    //Method to get the current balance of the account
    public double getBalance() {
        return balance;
    }

    //Getters for account number and account holder
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolder() {
        return accountHolder;
    }   
    
    
    //Deposit for the account of the user/client
    public void deposit(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
        throw new IllegalArgumentException("Error: Deposit amount must be positive.");
    }
    balance += amount;
    System.out.printf("Successfully deposited R%.2f. New balance: R%.2f%n", amount, balance);
}


    //Adjust balance only if no expetion is thrown
public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
    withdraw(amount, "Withdrawal completed. New balance: R%.2f");
}

public void withdraw(double amount, String messageFormat) throws InsufficientFundsException {
    if (amount <= 0) {
        throw new IllegalArgumentException("Invalid withdrawal amount. Amount must be higher than zero.");
    }
    
    System.out.println("\nCurrent balance before withdrawal: R" + String.format("%.2f", balance));
    
    if (balance < amount) {
        throw new InsufficientFundsException("Insufficient funds. Current balance: R" + 
            String.format("%.2f", balance) + " | Attempted withdrawal: R" + String.format("%.2f", amount));
    }
    
    balance -= amount;
    System.out.println(String.format(messageFormat, balance));
    System.out.println("Amount withdrawn: R" + String.format("%.2f", amount));
    System.out.println("Remaining balance: R" + String.format("%.2f", balance));
}

    //Custom exception class for insufficient funds
    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }


}

