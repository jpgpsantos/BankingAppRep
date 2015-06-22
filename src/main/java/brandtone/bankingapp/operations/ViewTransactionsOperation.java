/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp.operations;

import brandtone.bankingapp.AppSession;
import brandtone.bankingapp.Utils;
import brandtone.bankingapp.domainmodel.Account;
import brandtone.bankingapp.exceptions.ParseCommandException;
import brandtone.bankingapp.exceptions.ValidationCommandException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author XKEP007
 */
public class ViewTransactionsOperation {

    private List<String> commandInputs;

    private String parsed_name;
    private String parsed_value;

    private Account account;
    private BigDecimal value;

    public ViewTransactionsOperation(List<String> commandInputs) {
        this.commandInputs = commandInputs;
        try {
            this.parseCommand();
            this.validateCommand();
            this.executeCommand();
        } catch (ParseCommandException parseEx) {
            System.err.println("Invalid Command Inputs!");
            printCommandInstructions();
        } catch (ValidationCommandException validationEx) {
        }
    }

    public void parseCommand() throws ParseCommandException {
        if (commandInputs.size() == 3) {
            this.parsed_name = commandInputs.get(1);
            this.parsed_value = commandInputs.get(2);
            try {
                this.value = Utils.convertStringToBigDecimal(parsed_value);
            } catch (ParseException pe) {
                throw new ParseCommandException();
            }
        } else {
            this.printCommandInstructions();
            throw new ParseCommandException();
        }
    }

    public void validateCommand() throws ValidationCommandException {

        this.account = AppSession.accountsHashMap.get(parsed_name);
        if (account == null) {
            System.out.println("Credit not completed: account do not exists!");
            throw new ValidationCommandException();
        }
    }

    public void executeCommand() {
        System.out.println("Source Account || Target Account || amount || Date ");
        Set<String> keys = AppSession.accountsHashMap.keySet();
        Iterator keysIt = keys.iterator();
        while (keysIt.hasNext()) {
            String currentKey = (String) keysIt.next();
            Account currrentAccount = AppSession.accountsHashMap.get(currentKey);
            System.out.println(currrentAccount.getName() + " || " + currrentAccount.getAddress() + " || " + currrentAccount.getPhone_number() + " || " + currrentAccount.getBalance());
        }
    }

    public void printCommandInstructions() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("View Transactions Command:");
        System.out.println("    viewTransactions");
        System.out.println("---------------------------------------------------------------------");
    }
}
