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
public class PaymentFile {
  private int id;
  private String name;
  private String status;

  public PaymentFile(int id, String name, String status) {
    this.id = id;
    this.name = name;
    this.status = status;
  }

  public PaymentFile(String name, String status) {
    this.id = 0;
    this.name = name;
    this.status = status;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public static PaymentFile load(int id) {
    final int fid = id;
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<PaymentFile>() {

      protected PaymentFile job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, Name, Status FROM PaymentFiles WHERE Id = " + fid);
        try {
          if (st.step()) {
            return new PaymentFile(st.columnInt(0), st.columnString(1), st.columnString(2));
          }
          return null;
        } finally {
          st.dispose();
        }
      }
    }).complete();
  }

  public static List<PaymentFile> loadAll() {
    return DbHandler.getInstance().getQueue().execute(new SQLiteJob<List>() {

      protected List job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("SELECT Id, Name, Status FROM PaymentFiles ORDER BY Name DESC");
        try {
          List l = new ArrayList();
          while (st.step()) {
            l.add(new PaymentFile(st.columnInt(0), st.columnString(1), st.columnString(2)));
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
        SQLiteStatement st = connection.prepare("SELECT IFNULL(MAX(Id) + 1, 1) FROM PaymentFiles");
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
    final String fstatus = status;
    DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

      protected Void job(SQLiteConnection connection) throws SQLiteException {
        SQLiteStatement st = connection.prepare("INSERT INTO PaymentFiles (Id, Name, Status) VALUES (" + fid + ", '" + fname + "', '" + fstatus + "')");
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
