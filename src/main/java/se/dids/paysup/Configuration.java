/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author david
 */
public class Configuration {
  
  private PropertiesConfiguration config;

  private Configuration() {
  }

  public static Configuration getInstance() {
    return SingletonHolder.getInstance();
  }

  static class SingletonHolder {
    static Configuration instance;

    static {
      instance = new Configuration();
    }

    static Configuration getInstance() {
      return instance;
    }
  }

  public PropertiesConfiguration getConfig() {
    return config;
  }

  public void saveConfig() {
    try {
      config.save("application.properties");
    } catch (ConfigurationException ex) {
      Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void initialize() {
    try {
      config = new PropertiesConfiguration("application.properties");
    } catch (ConfigurationException ex) {
      Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


}
