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
class AccountType {

  private int id;
  private String name;

  public AccountType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public AccountType(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static AccountType load(String name) {
    final String fname = name;
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<AccountType>() {
      protected AccountType job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, Name FROM AccountTypes WHERE Name = '" + fname + "'");
        try {
          if (st.step()) {
            return new AccountType(st.columnInt(0), st.columnString(1));
          }
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public static List<AccountType> loadAll() {
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<List>() {

      protected List job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, Name FROM AccountTypes");
        try {
          List l = new ArrayList();
          while (st.step()) {
            l.add(new AccountType(st.columnInt(0), st.columnString(1)));
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
        SQLiteStatement st = connection.prepare("SELECT IFNULL(MAX(Id) + 1, 1) FROM AccountTypes");
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
    DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

      protected Void job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("INSERT INTO AccountTypes (Id, Name) VALUES (" + fid + ", '" + fname + "')");
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
