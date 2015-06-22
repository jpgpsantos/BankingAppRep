/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp;

import brandtone.bankingapp.operations.CreateAccountOperation;
import brandtone.bankingapp.operations.CreditAccountOperation;
import brandtone.bankingapp.operations.ListAccountsOperation;
import brandtone.bankingapp.operations.MakeTransferOperation;
import brandtone.bankingapp.operations.ViewTransactionsOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author XKEP007
 */
public class AppMainController {

    private HashMap<String, String> validComands;

    private String currentLineInput;

    public AppMainController() {
        this.validComands = new HashMap<String, String>();
        this.validComands.put("createAccount", "createAccount");
        this.validComands.put("listAccounts", "listAccounts");
        this.validComands.put("creditAccount", "creditAccount");
        this.validComands.put("makeTransfer", "makeTransfer");
        this.validComands.put("viewTransactions", "viewTransactions");

        //  this.accountsHashMap = new HashMap<String, Account>();
        //  this.transactionsArray = new ArrayList<Transaction>();
    }

    public void run() {
        System.out.println("Welcome to the Banking System!");
        printInstructions();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while ((currentLineInput = br.readLine()) != null) {

                if (!currentLineInput.isEmpty()) {
                    List<String> splited = Utils.splitCommandIntoArray(currentLineInput);
                    if (validComands.containsKey(splited.get(0))) {
                        parseCommand(splited);

                    } else {
                        System.out.println("Invalid command!");
                        printInstructions();
                    }
                } else {
                   // System.out.println("Invalid command!");
                   // printInstructions();
                }
//                System.out.print("Enter Integer:");
//                int i = Integer.parseInt(br.readLine());
                System.out.println();

            }

        }  catch (IOException ioe) {
          System.out.println("An IO Exception ocurred");
        }
    }

//    private void processCreateAccountCommand(String name, String address, String phone_number, String balance) {
//        
//
//    }
//    private void processCreditAccountCommand(String name, String value) {
//        System.out.println("entrou no processCreditAccountCommand com os seguintes inputs:");
//        System.out.println("name:" + name);
//        System.out.println("value:" + value);
//
//        Account account = AppSession.accountsHashMap.get(name);
//        if (account != null) {
//            System.out.println("Encontrei nenhuma conta com esse valor!!!");
//            BigDecimal valueBigDecimal = Utils.convertStringToBigDecimal(value);
//            BigDecimal sum = account.getBalance().add(valueBigDecimal);
//            account.setBalance(sum);
//        } else {
//            System.out.println("NÃO encontrei nenhuma conta com esse valor");
//        }
//        System.out.println("Account Sucessfully Credited!");
//    }
//    private void processTransferCommand(String sourceAccountName, String targetAccountName, String value) {
//       
//    }
//    private void processListAccountsCommand() {
////        System.out.println("entrou no listAccountsCommand com os seguintes inputs:");
//        System.out.println("Name || Address || phone_number || Balance ");
//        Set<String> keys = AppSession.accountsHashMap.keySet();
//        Iterator keysIt = keys.iterator();
//        while (keysIt.hasNext()) {
//            String currentKey = (String) keysIt.next();
//            Account currrentAccount = AppSession.accountsHashMap.get(currentKey);
//            System.out.println(currrentAccount.getName() + " || " + currrentAccount.getAddress() + " || " + currrentAccount.getPhone_number() + " || " + currrentAccount.getBalance());
//
//        }
//    }
//    private void processViewTransactionsCommand() {
////        System.out.println("entrou no listAccountsCommand com os seguintes inputs:");
//        System.out.println("Source Account || Target Account || amount || Date ");
//        Set<String> keys = AppSession.accountsHashMap.keySet();
//        Iterator keysIt = keys.iterator();
//        while (keysIt.hasNext()) {
//            String currentKey = (String) keysIt.next();
//            Account currrentAccount = AppSession.accountsHashMap.get(currentKey);
//            System.out.println(currrentAccount.getName() + " || " + currrentAccount.getAddress() + " || " + currrentAccount.getPhone_number() + " || " + currrentAccount.getBalance());
//        }
//    }
    public void printInstructions() {
        System.out.println("How to use:");
        System.out.println("- Create Account Command:");
        System.out.println("    createAccount <name> <address> <phone_number> <balance>");
        System.out.println("Ex: createAccount \"accountName1\" \"accountAdress1\" 213334567 21,8");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("- List Accounts Command:");
        System.out.println("    listAccounts");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("- Credit Account Command:");
        System.out.println("    creditAccount <accountname> <value>");
        System.out.println("Ex: creditAccount \"accountName1\" 21,8");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("- Make Transfer Command:");
        System.out.println("    makeTransfer <sourceaccountname> <targetaccountname> <value>");
        System.out.println("Ex: makeTransfer \"accountName1\" \"accountName2\" 21,8");

        System.out.println("---------------------------------------------------------------------");

        System.out.println("- View Transactions Command:");
        System.out.println("    viewTransactions");
        System.out.println("---------------------------------------------------------------------");



    }

    private void parseCommand(List<String> splited) {

        if (splited.get(0).equals("createAccount")) {
            CreateAccountOperation createAccountOperation = new CreateAccountOperation(splited);
        }

        if (splited.get(0).equals("listAccounts")) {
            ListAccountsOperation listAccountsOperation = new ListAccountsOperation();
        }

        if (splited.get(0).equals("creditAccount")) {
            //  this.processCreditAccountCommand(line, line);
            CreditAccountOperation creditAccountOperation = new CreditAccountOperation(splited);
        }

        if (splited.get(0).equals("makeTransfer")) {

            MakeTransferOperation makeTransferOperation = new MakeTransferOperation(splited);
            // this.processCreditAccountCommand(line, line);

        }

        if (splited.get(0).equals("viewTransactions")) {
            ViewTransactionsOperation viewTransactionsOperation = new ViewTransactionsOperation();
        }

    }

}
