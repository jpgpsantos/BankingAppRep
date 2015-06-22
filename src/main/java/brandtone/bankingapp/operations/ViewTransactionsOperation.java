/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp.operations;

import brandtone.bankingapp.AppSession;
import brandtone.bankingapp.Utils;
import brandtone.bankingapp.domainmodel.Account;
import brandtone.bankingapp.domainmodel.Transaction;
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

    public ViewTransactionsOperation() {
        // this.commandInputs = commandInputs;
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
    }

    public void validateCommand() throws ValidationCommandException {
    }

    public void executeCommand() {
        System.out.println("Source Account || Target Account || amount || Date ");
        Set<String> keys = AppSession.accountsHashMap.keySet();
        Iterator keysIt = keys.iterator();
        for (int i = 0; i < AppSession.transactionsArray.size(); i++) {
            Transaction currrentTransaction = AppSession.transactionsArray.get(i);
            System.out.println(currrentTransaction.getSourceCustomerAccount().getName()
                    + " || "
                    + currrentTransaction.getTargetCustomerAccount().getName()
                    + " || "
                    + currrentTransaction.getValue()
                    + " || "
                    + currrentTransaction.getOperationDate());
        }
        System.out.println("----------------------------------------------------------");
    }

    public void printCommandInstructions() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("View Transactions Command:");
        System.out.println("    viewTransactions");
        System.out.println("---------------------------------------------------------------------");
    }
}
