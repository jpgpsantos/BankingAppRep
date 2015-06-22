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
import java.util.List;

/**
 *
 * @author XKEP007
 */
public class CreateAccountOperation {

    private List<String> commandInputs;

    private String accountName;
    private String address;
    private String phone_number;
    private BigDecimal balance;

    public CreateAccountOperation() {
    }

    public CreateAccountOperation(List<String> commandInputs) {
        this.commandInputs = commandInputs;
        try {
            this.parseCommand();
            this.validateCommand();
            this.executeCommand();
        } catch (ParseCommandException parseEx) {
            System.out.println("Invalid Command Inputs!");
            printCommandInstructions();
        } catch (ValidationCommandException validationEx) {
        }
    }

    private void parseCommand() throws ParseCommandException {
        if (commandInputs.size() == 5) {
            this.accountName = commandInputs.get(1);
            this.address = commandInputs.get(2);
            this.phone_number = commandInputs.get(3);
            try {
                BigDecimal ballanceBigDecimal = Utils.convertStringToBigDecimal(commandInputs.get(4));
                this.balance = ballanceBigDecimal;
            } catch (ParseException pe) {
                throw new ParseCommandException();
            }
        } else {
            this.printCommandInstructions();
            throw new ParseCommandException();
        }
    }

    private void validateCommand() throws ValidationCommandException {
        if (AppSession.accountsHashMap.get(accountName) != null) {
            System.out.println("Account not created: account already exists!");
            throw new ValidationCommandException();
        }
    }

    private void executeCommand() {
        Account account = new Account();
        account.setAddress(address);

        account.setBalance(balance);
        account.setName(accountName);
        account.setPhone_number(phone_number);

        AppSession.accountsHashMap.put(account.getName(), account);
        System.out.println("Account Sucessfully created!");
    }

    private void printCommandInstructions() {
        System.out.println("- Create Account Command:");
        System.out.println("    createAccount <name> <address> <phone_number> <balance>");
        System.out.println("Ex: createAccount \"accountName1\" \"accountAdress1\" 213334567 21,8");
        System.out.println("-------------------------------------------------------------------");
    }
}
