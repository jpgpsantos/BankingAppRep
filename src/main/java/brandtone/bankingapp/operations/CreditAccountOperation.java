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
public class CreditAccountOperation {

    private List<String> commandInputs;

    private String parsed_name;
    private String parsed_value;

    private Account account;
    private BigDecimal value;

    public CreditAccountOperation(List<String> commandInputs) {
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

    private void validateCommand() throws ValidationCommandException {

        this.account = AppSession.accountsHashMap.get(parsed_name);
        if (account == null) {
            System.out.println("Credit not completed: account do not exists!");
            throw new ValidationCommandException();
        }
    }

    private void executeCommand() {
        // BigDecimal valueBigDecimal = Utils.convertStringToBigDecimal(value);
        BigDecimal sum = account.getBalance().add(value);
        account.setBalance(sum);
        System.out.println("Account Sucessfully Credited!");
    }

    private void printCommandInstructions() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("- Credit Account Command:");
        System.out.println("    creditAccount <accountname> <value>");
        System.out.println("Ex: creditAccount \"accountName1\" 21,8");
        System.out.println("---------------------------------------------------------------------");
    }

}
