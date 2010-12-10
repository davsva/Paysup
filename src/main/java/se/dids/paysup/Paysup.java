/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import java.util.Date;

/**
 *
 * @author david
 */
public class Paysup {

    public void Paysup() {
    }

    public void initialize(String args[]) {
      Configuration.getInstance().initialize();

      // Is this the first run ever?
      boolean firstRun = false;
      if (Configuration.getInstance().getConfig().getProperty("first_run").equals("true")) {
        firstRun = true;
      }
      if (firstRun) {
        /* TODO
          Configuration.getInstance().getConfig().setProperty("first_run", new Date().toString());
          Configuration.getInstance().saveConfig();
         */
        System.out.println("Time to create a new database");
        java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
            new NewDatabase().setVisible(true);
          }
        });
      } else {
        System.out.println("Loading ");


      }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
      Paysup app = new Paysup();
      app.initialize(args);
    }
}
