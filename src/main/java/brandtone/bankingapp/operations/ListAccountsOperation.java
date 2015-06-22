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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author XKEP007
 */
public class ListAccountsOperation {

    private List<String> commandInputs;

    public ListAccountsOperation() {
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

    private void parseCommand() throws ParseCommandException {
    }

    private void validateCommand() throws ValidationCommandException {

    }

    public void executeCommand() {
        System.out.println("Name || Address || phone_number || Balance ");
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
        System.out.println("- List Accounts Command:");
        System.out.println("    listAccounts");
        System.out.println("---------------------------------------------------------------------");
    }
}
