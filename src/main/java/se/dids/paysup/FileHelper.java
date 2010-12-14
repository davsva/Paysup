/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class FileHelper {

  public static String readFileAsString(String filePath) {
    try {
      System.out.println(new File(".").getCanonicalPath());
    } catch (IOException ex) {
      Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    byte[] buffer = new byte[(int) new File(filePath).length()];
    BufferedInputStream f = null;
    try {
      f = new BufferedInputStream(new FileInputStream(filePath));
      f.read(buffer);
    } catch (IOException ex) {
      Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (f != null) {
        try {
          f.close();
        } catch (IOException ignored) {
        }
      }
    }
    return new String(buffer);
  }
}
