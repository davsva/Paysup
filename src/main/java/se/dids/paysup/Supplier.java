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
import java.util.List;

/**
 *
 * @author david
 */
class Supplier {

  private int id;
  private int accountTypeId;
  private String accountNo;
  private String name;

  public Supplier(int id, int accountTypeId, String accountNo, String name) {
    this.id = id;
    this.accountTypeId = accountTypeId;
    this.accountNo = accountNo;
    this.name = name;
  }

  public Supplier(int accountTypeId, String accountNo, String name) {
    this.id = 0;
    this.accountTypeId = accountTypeId;
    this.accountNo = accountNo;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAccountTypeId() {
    return accountTypeId;
  }

  public void setAccountTypeId(int accountTypeId) {
    this.accountTypeId = accountTypeId;
  }

  public static Supplier load(int accountTypeId, String accountNo) {
    final int faccountTypeId = accountTypeId;
    final String faccountNo = accountNo;
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<Supplier>() {

      protected Supplier job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, AccountTypeId, AccountNo, Name FROM Suppliers WHERE AccountTypeId = " + faccountTypeId + " AND AccountNo = '" + faccountNo + "'");
        try {
          if (st.step()) {
            return new Supplier(st.columnInt(0), st.columnInt(1), st.columnString(2), st.columnString(3));
          }
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public static List<Supplier> loadAll() {
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<List>() {

      protected List job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, AccountTypeId, AccountNo, Name FROM Suppliers ORDER BY Name ASC");
        try {
          List l = new ArrayList();
          while (st.step()) {
            l.add(new Supplier(st.columnInt(0), st.columnInt(1), st.columnString(2), st.columnString(3)));
          }
          return l;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public void insert() {
    id = DbHandler.getInstance().getQueue().execute(new SQLiteJob<Integer>() {

      protected Integer job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT IFNULL(MAX(Id) + 1, 1) FROM Suppliers");
        try {
          st.step();
          return st.columnInt(0);
        } finally {
          st.dispose();
        }
      }
    }).complete();

    final int fid = id;
    final String fname = name;
    final int faccountTypeId = accountTypeId;
    final String faccountNo = accountNo;
    DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

      protected Void job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("INSERT INTO Suppliers (Id, AccountTypeId, AccountNo, Name) VALUES (" + fid + ", " + faccountTypeId + ", '" + faccountNo + "', '" + fname + "')");
        try {
          st.step();
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public void update() {
    final String fname = name;
    final int fid = id;
    DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

      protected Void job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("UPDATE Suppliers SET Name = '" + fname + "' WHERE Id = " + fid);
        try {
          st.step();
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public String toString() {
    return name;
  }
}
