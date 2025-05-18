package bank.app;

import bank.core.BankAccount;
import bank.core.BankAccount.InsufficientFundsException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BankApp{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        try{
            //Creating a new account
            System.out.println("Welcome to SOAR bank! You are about to create a new account.");

            System.out.println("\nEnter your name: ");
            String accountHolder = scanner.nextLine();

            account = new BankAccount(accountHolder, 100.00);
            System.out.println("\n Account created successfully!");
            System.out.println("\nAccount Number: " + account.getAccountNumber());
            System.out.println("\nInitial balance: R" + account.getBalance());

            //Transaction loop
            while (true){
                try{
                    System.out.println("\n1. Deposit\n2. Withdraw\n3. Check balance\n4. Exit");
                    
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    switch(choice){
                        case 1:
                            System.out.println("Enter the amount to deposit: ");
                            account.deposit(scanner.nextDouble());
                            break;
                        case 2:
                            System.out.println("Enter the amount to withdraw: ");
                            double amount = scanner.nextDouble();
                                account.withdraw(amount); 
                            break;

                        case 3:
                            System.out.println("Current balance: " + account.getBalance());
                            break;
                        case 4:
                            System.out.println("Thank you for your time, have a beautiful day.");
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
                catch(IllegalArgumentException | InsufficientFundsException e){
                    System.out.println("Error: "+ e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }
}