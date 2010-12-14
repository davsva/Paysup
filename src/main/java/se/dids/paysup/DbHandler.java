/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;


import com.almworks.sqlite4java.SQLite;
import com.almworks.sqlite4java.SQLiteQueue;
import java.io.File;

/**
 *
 * @author david
 */
public class DbHandler {

  private SQLiteQueue queue;

  private DbHandler() {
  }

  public static DbHandler getInstance() {
    return SingletonHolder.getInstance();
  }

  static class SingletonHolder {
    static DbHandler instance;

    static {
      instance = new DbHandler();
    }

    static DbHandler getInstance() {
      return instance;
    }
  }

  public SQLiteQueue getQueue() {
    return queue;
  }

  public void initialize(File db) {
    SQLite.setLibraryPath("target/lib");
    queue = new SQLiteQueue(db);
    queue.start();
  }
}
