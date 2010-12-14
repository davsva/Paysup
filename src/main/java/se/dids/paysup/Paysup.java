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
      boolean tempFirstRun = false;
      if (Configuration.getInstance().getConfig().getProperty("first_run").equals("true")) {
        tempFirstRun = true;
      }
      final boolean firstRun = tempFirstRun;

      // Launch the main window
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          MainFrame form = new MainFrame(firstRun);
          form.showFrame();
        }
      });
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
      Paysup app = new Paysup();
      app.initialize(args);
    }
}
