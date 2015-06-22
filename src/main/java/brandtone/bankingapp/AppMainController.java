/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp;

import brandtone.bankingapp.domainmodel.Account;
import brandtone.bankingapp.domainmodel.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author XKEP007
 */
public class AppMainController {

    private HashMap<String, Account> accountsHashMap;

    private ArrayList<Transaction> transactionsArray;

    public AppMainController() {
        this.accountsHashMap = new HashMap<String, Account>();
        this.transactionsArray = new ArrayList<Transaction>();
    }

    public void run() {
        System.out.println("1 - Create Account Command :");
        System.out.println("    createAccount <name> <address> <phone_number> <balance>");
        System.out.println("2 - List Accounts:");
        System.out.println("    listAccounts");
        System.out.println("3 - Credit Account Command:");
        System.out.println("    creditAccount <accountname> <value>");
        System.out.println("4 - Make Transfer");
        System.out.println("    makeTransfer <sourceaccountname> <targetaccountname> <value>");
        System.out.println("5 - View Transactions");
        System.out.println("    viewTransactions");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                // String s = br.readLine();
                System.out.println("String inserida: " + line);
                List<String> splited = this.splitCommandIntoArray2(line);
                System.out.println("array:" + splited);
                if (splited.get(0).equals("createAccount")) {
                    if (splited.size() == 5) {
                        System.out.println("passou a validacao da conta...vai criar");
                        String accountName = splited.get(1);
                        String address = splited.get(2);
                        String phone_number = splited.get(3);
                        String balance = splited.get(4);
                        this.processCreateAccountCommand(accountName, address, phone_number, balance);
                    } else {
                        System.out.println("regras de utilizacao do create account:");
                        System.out.println("createAccount <name> <address> <phone_number> <balance>");
                    }
                }

                if (splited.get(0).equals("listAccounts")) {
                    this.processListAccountsCommand();
                }

                if (splited.get(0).equals("creditAccount")) {
                    this.processCreditAccountCommand(line, line);
                    if (splited.size() == 3) {
                        System.out.println("passou a validacao da creditacoa de conta...vai creditar");
                        String accountName = splited.get(1);
                        String value = splited.get(2);
                        this.processCreditAccountCommand(accountName, value);
                    } else {
                        System.out.println("regras de utilizacao do create account:");
                        System.out.println("createAccount <name> <address> <phone_number> <balance>");

                    }
                }

                if (splited.get(0).equals("makeTransfer")) {
                    this.processCreditAccountCommand(line, line);
                    if (splited.size() == 4) {
                        System.out.println("passou a validacao da transferencia de conta...vai transferir");
                        String sourceAccountName = splited.get(1);
                        String targetAccountName = splited.get(2);
                        String value = splited.get(3);
                        this.processTransferCommand(sourceAccountName, targetAccountName, value);
                    } else {
                        System.out.println("regras de utilizacao do create account:");
                        System.out.println("createAccount <name> <address> <phone_number> <balance>");

                    }
                }

                if (splited.get(0).equals("viewTransactions")) {
                    this.processViewTransactionsCommand();
                }

//                System.out.print("Enter Integer:");
//                int i = Integer.parseInt(br.readLine());
            }

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        } catch (IOException ioe) {
            System.err.println("Invalid Format!");
        }
    }

    private void processCreateAccountCommand(String name, String address, String phone_number, String balance) {
        System.out.println("entrou no processCreateAccountCommand com os seguintes inputs:");
        System.out.print("name:" + name);
        System.out.println("address:" + address);
        System.out.println("phone_number:" + phone_number);
        System.out.println("balance:" + balance);

        Account account = new Account();
        account.setAddress(address);
        BigDecimal ballanceBigDecimal = convertStringToBigDecimal(balance);
        account.setBalance(ballanceBigDecimal);
        account.setName(name);
        account.setPhone_number(phone_number);

        accountsHashMap.put(account.getName(), account);
        System.out.println("Account Sucessfully created!");

    }

    private BigDecimal convertStringToBigDecimal(String inputMoneyString) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        String pattern = "###,##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

// parse the string
        try {
            BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(inputMoneyString);
            System.out.println(bigDecimal);
            return bigDecimal;
        } catch (Exception ex) {
            System.out.println("ocorreu erro parsing de money");
            return null;
        }
    }

    private void processCreditAccountCommand(String name, String value) {
        System.out.println("entrou no processCreditAccountCommand com os seguintes inputs:");
        System.out.println("name:" + name);
        System.out.println("value:" + value);

        Account account = accountsHashMap.get(name);
        if (account != null) {
            System.out.println("Encontrei nenhuma conta com esse valor!!!");
            BigDecimal valueBigDecimal = convertStringToBigDecimal(value);
            BigDecimal sum = account.getBalance().add(valueBigDecimal);
            account.setBalance(sum);
        } else {
            System.out.println("NÃO encontrei nenhuma conta com esse valor");
        }
        System.out.println("Account Sucessfully Credited!");
    }

    private void processTransferCommand(String sourceAccountName, String targetAccountName, String value) {
        System.out.println("entrou no processCreditAccountCommand com os seguintes inputs:");
        System.out.println("name:" + sourceAccountName);
        System.out.println("targetAccountName:" + targetAccountName);
        System.out.println("value:" + value);
        Account sourceAccount = accountsHashMap.get(sourceAccountName);
        Account targetAccount = accountsHashMap.get(targetAccountName);
        if (sourceAccount != null && targetAccount != null) {
            System.out.println("Encontrei  conta com esse nomes!!!");
            BigDecimal valueBigDecimal = convertStringToBigDecimal(value);

            BigDecimal sourceNewBallance = sourceAccount.getBalance().subtract(valueBigDecimal);
            sourceAccount.setBalance(sourceNewBallance);

            BigDecimal targetNewBallance = targetAccount.getBalance().add(valueBigDecimal);
            targetAccount.setBalance(targetNewBallance);
        } else {
            if (sourceAccount == null) {
                System.out.println("NÃO encontrei nenhuma conta origem com esse nome");
            }
        }
        System.out.println("Account Sucessfully Credited!");
    }

    private void processListAccountsCommand() {
//        System.out.println("entrou no listAccountsCommand com os seguintes inputs:");
        System.out.println("Name || Address || phone_number || Balance ");
        Set<String> keys = accountsHashMap.keySet();
        Iterator keysIt = keys.iterator();
        while (keysIt.hasNext()) {
            String currentKey = (String) keysIt.next();
            Account currrentAccount = accountsHashMap.get(currentKey);
            System.out.println(currrentAccount.getName() + " || " + currrentAccount.getAddress() + " || " + currrentAccount.getPhone_number() + " || " + currrentAccount.getBalance());

        }
    }

    private void processViewTransactionsCommand() {
//        System.out.println("entrou no listAccountsCommand com os seguintes inputs:");
        System.out.println("Source Account || Target Account || amount || Date ");
        Set<String> keys = accountsHashMap.keySet();
        Iterator keysIt = keys.iterator();
        while (keysIt.hasNext()) {
            String currentKey = (String) keysIt.next();
            Account currrentAccount = accountsHashMap.get(currentKey);
            System.out.println(currrentAccount.getName() + " || " + currrentAccount.getAddress() + " || " + currrentAccount.getPhone_number() + " || " + currrentAccount.getBalance());
        }
    }

    public HashMap<String, Account> getAccountsHashMap() {
        return accountsHashMap;
    }

    public void setAccountsHashMap(HashMap<String, Account> accountsHashMap) {
        this.accountsHashMap = accountsHashMap;
    }

    public ArrayList<Transaction> getTransactionsArray() {
        return transactionsArray;
    }

    public void setTransactionsArray(ArrayList<Transaction> transactionsArray) {
        this.transactionsArray = transactionsArray;
    }

    public String[] splitCommandIntoArray(String inputString) {
        //  final String s = "\"Video or movie\"    \"parent\"    \"Media or entertainment\"    \"1\" \"1\" \"1\" \"0\" \"0\"";
        String[] result = inputString.split("(?<=\") *(?=\")");
//        for (final String x : t) {
//            System.out.println(x);
//        }
        return result;
    }

    public List<String> splitCommandIntoArray2(String inputString) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(inputString);
        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        return matchList;
    }
}
