/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import java.util.Date;
import java.util.Locale;

/**
 *
 * @author david
 */
public class Paysup {

    public void Paysup() {
    }

    public void initialize(String args[]) {
      Configuration.getInstance().initialize();

      // Set default locale
      String strLocale = (String)Configuration.getInstance().getConfig().getProperty("default_locale");
      if (strLocale != null && ! strLocale.isEmpty()) {
        Locale locale = null;
        String[] result = strLocale.split("_");
        if (result.length == 3) {
          locale = new Locale(result[0], result[1], result[2]);
        } else if (result.length == 2) {
          locale = new Locale(result[0], result[1]);
        } else if (result.length == 1) {
          locale = new Locale(result[0]);
        }
        System.out.println(locale.getLanguage() + "_" + locale.getCountry() + "_" + locale.getVariant());
        Locale.setDefault(locale);
      }

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
