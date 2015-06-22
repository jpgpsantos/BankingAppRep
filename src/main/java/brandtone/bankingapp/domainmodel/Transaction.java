/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brandtone.bankingapp.domainmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author XKEP007
 */
public class Transaction {

    private Account sourceCustomerAccount;

    private Account targetCustomerAccount;

    private BigDecimal value;

    private Date operationDate;

    public Account getSourceCustomerAccount() {
        return sourceCustomerAccount;
    }

    public void setSourceCustomerAccount(Account sourceCustomerAccount) {
        this.sourceCustomerAccount = sourceCustomerAccount;
    }

    public Account getTargetCustomerAccount() {
        return targetCustomerAccount;
    }

    public void setTargetCustomerAccount(Account targetCustomerAccount) {
        this.targetCustomerAccount = targetCustomerAccount;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

}
