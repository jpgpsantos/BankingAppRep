/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp;

import brandtone.bankingapp.domainmodel.Account;
import brandtone.bankingapp.domainmodel.Transaction;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author XKEP007
 */
public class AppSession {

    public static final HashMap<String, Account> accountsHashMap = new HashMap<String, Account>();

    public static final ArrayList<Transaction> transactionsArray = new ArrayList<Transaction>();

}
