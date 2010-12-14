/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;

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

}
