/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author david
 */
public class Payment {
  private int id;
	private int paymentFileId;
	private Date dueDate;
  private String accountTypeName;
  private String accountNo;
	private String supplierName;
	private Double amount;
	private String reference;

  public Payment(int id, int paymentFileId, Date dueDate, String accountTypeName, String accountNo, String supplierName, Double amount, String reference) {
    this.id = id;
    this.paymentFileId = paymentFileId;
    this.dueDate = dueDate;
    this.accountTypeName = accountTypeName;
    this.accountNo = accountNo;
    this.supplierName = supplierName;
    this.amount = amount;
    this.reference = reference;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAccountTypeName() {
    return accountTypeName;
  }

  public void setAccountTypeName(String accountTypeName) {
    this.accountTypeName = accountTypeName;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPaymentFileId() {
    return paymentFileId;
  }

  public void setPaymentFileId(int paymentFileId) {
    this.paymentFileId = paymentFileId;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }
  
  public static Payment load(int id) {
    final int fid = id;
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<Payment>() {

      protected Payment job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, PaymentFileId, DueDate, AccountTypeName, AccountNo, SupplierName, Amount, Reference FROM Payments WHERE Id = " + fid);
        try {
          if (st.step()) {
            return new Payment(st.columnInt(0), st.columnInt(1), DateHelper.toDate(st.columnString(2)), st.columnString(3), st.columnString(4), st.columnString(5), st.columnDouble(6), st.columnString(7));
          }
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public static List<Payment> loadAll(int paymentFileId) {
    final int fpaymentFileId = paymentFileId;
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<List>() {

      protected List job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, PaymentFileId, DueDate, AccountTypeName, AccountNo, SupplierName, Amount, Reference FROM Payments WHERE PaymentFileId = " + fpaymentFileId + " ORDER BY DueDate ASC");
        try {
          List l = new ArrayList();
          while (st.step()) {
            l.add(new Payment(st.columnInt(0), st.columnInt(1), DateHelper.toDate(st.columnString(2)), st.columnString(3), st.columnString(4), st.columnString(5), st.columnDouble(6), st.columnString(7)));
          }
          return l;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public void insert() {
    final int fpaymentFileId = paymentFileId;
    final String fdueDate = DateHelper.toIso8601(dueDate);
    final String faccountTypeName = accountTypeName;
    final String faccountNo = accountNo;
    final String fsupplierName = supplierName;
    final double famount = amount;
    final String freference = reference;

    DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

      protected Void job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("INSERT INTO Payments (PaymentFileId, DueDate, AccountTypeName, AccountNo, SupplierName, Amount, Reference) VALUES (" + fpaymentFileId + ", '" + fdueDate + ", '" + faccountTypeName + ", '" + faccountNo + ", '" + fsupplierName + ", '" + famount + ", '" + freference + "')");
        try {
          st.step();
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }
}
