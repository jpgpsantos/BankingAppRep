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
import java.util.Date;
import java.util.List;

/**
 *
 * @author XKEP007
 */
public class MakeTransferOperation {

    private List<String> commandInputs;

    private String parsed_source_account;
    private String parsed_target_account;
    private String parsed_value;

    private Account source_account;
    private Account target_account;
    private BigDecimal value;

    public MakeTransferOperation(List<String> commandInputs) {
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

    public void parseCommand() throws ParseCommandException {
        if (commandInputs.size() == 4) {
            this.parsed_source_account = commandInputs.get(1);
            this.parsed_target_account = commandInputs.get(2);
            this.parsed_value = commandInputs.get(3);
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

        this.source_account = AppSession.accountsHashMap.get(parsed_source_account);
        if (source_account == null) {
            System.out.println("Transfer not completed: source account do not exists!");
            throw new ValidationCommandException();
        }
        this.target_account = AppSession.accountsHashMap.get(parsed_target_account);
        if (target_account == null) {
            System.out.println("Credit not completed: target account do not exists!");
            throw new ValidationCommandException();
        }

        if (source_account != null && target_account != null && value != null) {
            if (source_account.getBalance().compareTo(value) < 0) {
                System.out.println("Credit not completed: target account do not exists!");
                throw new ValidationCommandException();
            }
        }
    }

    public void executeCommand() {
        BigDecimal sourceNewBallance = source_account.getBalance().subtract(value);
        source_account.setBalance(sourceNewBallance);
        BigDecimal targetNewBallance = target_account.getBalance().add(value);
        target_account.setBalance(targetNewBallance);

        //add transaction to the list
        Transaction transaction = new Transaction();
        transaction.setOperationDate(new Date());
        transaction.setSourceCustomerAccount(source_account);
        transaction.setTargetCustomerAccount(target_account);
        transaction.setValue(value);

        AppSession.transactionsArray.add(transaction);
        //END: add transaction to the list

        System.out.println("Tranfer executed sucessfully!");
    }

    public void printCommandInstructions() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("- Make Transfer Command:");
        System.out.println("    makeTransfer <sourceaccountname> <targetaccountname> <value>");
        System.out.println("Ex: makeTransfer \"accountName1\" \"accountName2\" 21,8");
        System.out.println("---------------------------------------------------------------------");
    }

}
