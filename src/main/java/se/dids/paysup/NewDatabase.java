/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewDatabase.java
 *
 * Created on 2010-nov-18, 15:56:36
 */

package se.dids.paysup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author david
 */
public class NewDatabase extends javax.swing.JFrame {

    /** Creates new form NewDatabase */
    public NewDatabase() {
        initComponents();
        initAvailiableBackends();
        initDefaults();
    }

    private void initAvailiableBackends() {
      File folder = new File((String)Configuration.getInstance().getConfig().getProperty("backend_dir"));
      File[] listOfFiles = folder.listFiles();
      ArrayList backends = new ArrayList();
      for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {

          // Ignore files starting with the underscore ("_") character
          if (! listOfFiles[i].getName().startsWith("_")) {
            backends.add(listOfFiles[i].getName());
          }
        }
      }      
      backendComboBox.setModel(new javax.swing.DefaultComboBoxModel(backends.toArray()));
    }

    private void initDefaults() {
      databaseNameTextField.setText((String)Configuration.getInstance().getConfig().getProperty("default_database_name"));
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
    jCheckBox1 = new javax.swing.JCheckBox();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    databaseLocationTextField = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    browseButton.setText("Browse...");
    browseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        browseButtonActionPerformed(evt);
      }
    });

    jButton2.setText("Cancel");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    createButton.setText("Create");
    createButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createButtonActionPerformed(evt);
      }
    });

    jCheckBox1.setSelected(true);
    jCheckBox1.setText("Make this the default database");

    jLabel1.setText("Create a New Database");

    jLabel2.setText("Backend");

    jLabel3.setText("Database Name");

    jLabel4.setText("Database Location");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addContainerGap(306, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(databaseLocationTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(browseButton)
                  .addComponent(createButton)))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(databaseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
              .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(backendComboBox, 0, 299, Short.MAX_VALUE))
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
          .addComponent(jCheckBox1))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
      File newFile = new File(databaseLocationTextField.getText() +
              System.getProperty("file.separator") +
              databaseNameTextField.getText() +
              "." +
              Configuration.getInstance().getConfig().getString("database_name_extension"));
      DbHandler.getInstance().initialize(newFile);
      this.dispose();
    }//GEN-LAST:event_createButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
      JFileChooser fileChooser = new JFileChooser();
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          databaseNameTextField.setText(file.getAbsolutePath());
      } else {
          System.out.println("File access cancelled by user.");
      }
    }//GEN-LAST:event_browseButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewDatabase().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox backendComboBox;
  private javax.swing.JButton browseButton;
  private javax.swing.JButton createButton;
  private javax.swing.JTextField databaseLocationTextField;
  private javax.swing.JTextField databaseNameTextField;
  private javax.swing.JButton jButton2;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  // End of variables declaration//GEN-END:variables

}
