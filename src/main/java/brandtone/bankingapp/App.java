package brandtone.bankingapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System!");

//        create account(s) - a user can create an account, associate a name with it, give it a unique
//
//account number, add a starting balance, etc.
//
//2. make lodgement - a user can lodge an amount into an account (balance increase)
//
//3. make transfer - a user can transfer an amount from one account to another (balance 
//
//transfer)
//
//4. view transactions - a user can view recent, or all, transactions for an account (statement)
        System.out.println("How to use: ");

        AppMainController appMainController = new AppMainController();
        appMainController.run();

    }

}
