/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewDatabaseDialog.java
 *
 * Created on 2010-dec-14, 09:37:40
 */
package se.dids.paysup;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author david
 */
public class NewDatabaseDialog extends javax.swing.JDialog {

  /** Creates new form NewDatabaseDialog */
  public NewDatabaseDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    initAvailiableBackends();
    initDefaults();
  }

  private void initAvailiableBackends() {
    File folder = new File((String) Configuration.getInstance().getConfig().getProperty("backend_dir"));
    File[] listOfFiles = folder.listFiles();
    ArrayList backends = new ArrayList();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {

        // Ignore files starting with the underscore ("_") character
        if (!listOfFiles[i].getName().startsWith("_")) {
          backends.add(listOfFiles[i].getName());
        }
      }
    }
    backendComboBox.setModel(new javax.swing.DefaultComboBoxModel(backends.toArray()));
  }

  private void initDefaults() {
    databaseNameTextField.setText((String) Configuration.getInstance().getConfig().getProperty(java.util.ResourceBundle.getBundle("PaysupResource").getString("DEFAULT_DATABASE_NAME")));
    String dir = new String();
    try {
      dir = new File(".").getCanonicalPath() + System.getProperty("file.separator") + (String) Configuration.getInstance().getConfig().getProperty("database_dir");
    } catch (IOException ex) {
      Logger.getLogger(NewDatabase.class.getName()).log(Level.SEVERE, null, ex);
    }
    databaseLocationTextField.setText(dir);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    backendComboBox = new javax.swing.JComboBox();
    databaseNameTextField = new javax.swing.JTextField();
    browseButton = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    createButton = new javax.swing.JButton();
    defaultDatabaseCheckBox = new javax.swing.JCheckBox();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    databaseLocationTextField = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("PaysupResource"); // NOI18N
    browseButton.setText(bundle.getString("BROWSE...")); // NOI18N
    browseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        browseButtonActionPerformed(evt);
      }
    });

    jButton2.setText(bundle.getString("CANCEL")); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    createButton.setText(bundle.getString("CREATE")); // NOI18N
    createButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createButtonActionPerformed(evt);
      }
    });

    defaultDatabaseCheckBox.setSelected(true);
    defaultDatabaseCheckBox.setText(bundle.getString("MAKE THIS THE DEFAULT DATABASE")); // NOI18N

    jLabel1.setText(bundle.getString("CREATE A NEW DATABASE")); // NOI18N

    jLabel2.setText(bundle.getString("BACKEND")); // NOI18N

    jLabel3.setText(bundle.getString("DATABASE NAME")); // NOI18N

    jLabel4.setText(bundle.getString("DATABASE LOCATION")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addContainerGap(314, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(databaseLocationTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(browseButton)
                  .addComponent(createButton)))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(databaseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(defaultDatabaseCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE))
              .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(backendComboBox, 0, 321, Short.MAX_VALUE))
              .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
            .addGap(25, 25, 25))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(backendComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(databaseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(defaultDatabaseCheckBox))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(databaseLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(browseButton))
        .addGap(47, 47, 47)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(createButton))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
      JFileChooser fileChooser = new JFileChooser();
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        databaseNameTextField.setText(file.getAbsolutePath());
      } else {
        System.out.println(java.util.ResourceBundle.getBundle("PaysupResource").getString("FILE ACCESS CANCELLED BY USER."));
      }
}//GEN-LAST:event_browseButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      // TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
      File newFile = new File(databaseLocationTextField.getText()
              + System.getProperty(java.util.ResourceBundle.getBundle("PaysupResource").getString("FILE.SEPARATOR"))
              + databaseNameTextField.getText()
              + java.util.ResourceBundle.getBundle("PaysupResource").getString(".")
              + Configuration.getInstance().getConfig().getString("database_name_extension"));
      DbHandler.getInstance().initialize(newFile);

      // Read in model.SQL (the DDL) into string
      final String ddl = FileHelper.readFileAsString(java.util.ResourceBundle.getBundle("PaysupResource").getString("TARGET/CLASSES/MODEL.SQL"));

      // Initialize database
      DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

        protected Void job(SQLiteConnection connection) throws SQLiteException {
          connection.exec(ddl);
          return null;
        }
      }).complete();

      // Set the backend file name
      // TODO Maybe refactor and brake out the SQL
      DbHandler.getInstance().getQueue().execute(new SQLiteJob<Void>() {

        protected Void job(SQLiteConnection connection) throws SQLiteException {
          String backend = (String) backendComboBox.getSelectedItem();
          SQLiteStatement st = connection.prepare(java.util.ResourceBundle.getBundle("PaysupResource").getString("INSERT INTO CONFIGURATION (BACKENDFILE) VALUES ('") + backend + java.util.ResourceBundle.getBundle("PaysupResource").getString("')"));
          try {
            st.step();
            return null;
          } finally {
            st.dispose();
          }
        }
      }).complete();

      // Set the account types
      try {
        JAXBContext jc = JAXBContext.newInstance(java.util.ResourceBundle.getBundle("PaysupResource").getString("SE.DIDS.PAYSUP.BACKENDCONFIGURATION"));
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        se.dids.paysup.backendconfiguration.Configuration configuration = (se.dids.paysup.backendconfiguration.Configuration) unmarshaller.unmarshal(new File((String) Configuration.getInstance().getConfig().getProperty(java.util.ResourceBundle.getBundle("PaysupResource").getString("BACKEND_DIR")) + System.getProperty(java.util.ResourceBundle.getBundle("PaysupResource").getString("FILE.SEPARATOR")) + (String) backendComboBox.getSelectedItem()));
        List accountTypeList = configuration.getAccountTypes().getAccountType();
        for (Iterator iter = accountTypeList.iterator(); iter.hasNext();) {
          AccountType aT = new AccountType((String)iter.next());
          aT.insert();
        }
      } catch (JAXBException ex) {
        Logger.getLogger(NewDatabaseDialog.class.getName()).log(Level.SEVERE, null, ex);
      }

      // Is this the default database?
      if (defaultDatabaseCheckBox.isSelected()) {
        try {
          Configuration.getInstance().getConfig().setProperty(java.util.ResourceBundle.getBundle("PaysupResource").getString("DEFAULT_DATABASE"), newFile.getCanonicalPath());
          Configuration.getInstance().saveConfig();
        } catch (IOException ex) {
          Logger.getLogger(NewDatabaseDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.dispose();
}//GEN-LAST:event_createButtonActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        NewDatabaseDialog dialog = new NewDatabaseDialog(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

          public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
          }
        });
        dialog.setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox backendComboBox;
  private javax.swing.JButton browseButton;
  private javax.swing.JButton createButton;
  private javax.swing.JTextField databaseLocationTextField;
  private javax.swing.JTextField databaseNameTextField;
  private javax.swing.JCheckBox defaultDatabaseCheckBox;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  // End of variables declaration//GEN-END:variables
}
