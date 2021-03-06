/*
 * Paysup is copyright 2010 David Svanberg.
 * 
 * Paysup is MIT-licensed. See file LICENSE for further licensing information. 
 */
package se.dids.paysup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import se.dids.paysup.backendconfiguration.Configuration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

import com.almworks.sqlite4java.SQLite;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MainWindow {

  private JFrame frame;
  private JTextField txtAccountNo;
  private JTextField txtSupplierName;
  private JTextField txtDate;
  private JTextField txtReference;
  private JTextField txtTotalAmount;
  private JTextField txtAmount;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

      public void run() {
        try {
          MainWindow window = new MainWindow();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public MainWindow() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 601, 494);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

    JList list = new JList();
    frame.getContentPane().add(list);

    JPanel panel = new JPanel();
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    txtAccountNo = new JTextField();
    txtAccountNo.setBounds(242, 57, 86, 20);
    panel.add(txtAccountNo);
    txtAccountNo.setColumns(10);

    JList list_1 = new JList();
    list_1.setBounds(379, 15, 0, 0);
    panel.add(list_1);

    txtSupplierName = new JTextField();
    txtSupplierName.setBounds(346, 57, 215, 20);
    panel.add(txtSupplierName);
    txtSupplierName.setColumns(10);

    txtDate = new JTextField();
    txtDate.setBounds(346, 100, 86, 20);
    panel.add(txtDate);
    txtDate.setColumns(10);

    txtReference = new JTextField();
    txtReference.setBounds(242, 156, 166, 20);
    panel.add(txtReference);
    txtReference.setColumns(10);

    JList lstReference = new JList();
    lstReference.setBounds(10, 70, 109, 386);
    panel.add(lstReference);

    JButton btnAddPayment = new JButton("Add/update payment");
    btnAddPayment.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      }
    });
    btnAddPayment.setBounds(196, 198, 157, 23);
    panel.add(btnAddPayment);

    JList list_3 = new JList();
    list_3.setBounds(151, 232, 410, 126);
    panel.add(list_3);

    JScrollBar scrollBar = new JScrollBar();
    scrollBar.setBounds(563, 232, 17, 126);
    panel.add(scrollBar);

    JScrollBar scrollBar_1 = new JScrollBar();
    scrollBar_1.setBounds(124, 70, 17, 386);
    panel.add(scrollBar_1);

    JButton btnClear = new JButton("Clear");
    btnClear.setBounds(385, 198, 89, 23);
    panel.add(btnClear);

    txtTotalAmount = new JTextField();
    txtTotalAmount.setBounds(475, 369, 86, 20);
    panel.add(txtTotalAmount);
    txtTotalAmount.setColumns(10);

    JButton btnPreviewFile = new JButton("Preview file");
    btnPreviewFile.setBounds(360, 412, 102, 23);
    panel.add(btnPreviewFile);

    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 668, 21);
    panel.add(menuBar);

    JMenu mnFile = new JMenu("File");
    mnFile.setMnemonic('F');
    menuBar.add(mnFile);

    JMenuItem mntmNewPaymentFile = new JMenuItem("New file");
    mnFile.add(mntmNewPaymentFile);

    JMenuItem mntmExit = new JMenuItem("Exit");
    mntmExit.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        System.exit(0);
      }
    });
    mnFile.add(mntmExit);

    JMenu mnProperties = new JMenu("Properties");
    menuBar.add(mnProperties);

    JLabel lblSuppliers = new JLabel("Suppliers");
    lblSuppliers.setBounds(10, 45, 61, 14);
    panel.add(lblSuppliers);

    JLabel lblBgpg = new JLabel("Account type");
    lblBgpg.setBounds(163, 45, 79, 14);
    panel.add(lblBgpg);

    JLabel lblAccountNo = new JLabel("Account no");
    lblAccountNo.setBounds(242, 45, 86, 14);
    panel.add(lblAccountNo);

    JLabel lblSupplierNamen = new JLabel("Supplier name");
    lblSupplierNamen.setBounds(347, 45, 85, 14);
    panel.add(lblSupplierNamen);

    JLabel lblDate = new JLabel("Date");
    lblDate.setBounds(346, 88, 46, 14);
    panel.add(lblDate);

    txtAmount = new JTextField();
    txtAmount.setBounds(242, 100, 86, 20);
    panel.add(txtAmount);
    txtAmount.setColumns(10);

    JLabel lblAmount = new JLabel("Amount");
    lblAmount.setBounds(242, 88, 46, 14);
    panel.add(lblAmount);

    JLabel lblReference = new JLabel("Reference");
    lblReference.setBounds(242, 142, 61, 14);
    panel.add(lblReference);

    JButton btnCheckOcr = new JButton("Check OCR");
    btnCheckOcr.setBounds(413, 155, 100, 23);
    panel.add(btnCheckOcr);

    JButton btnSaveFile = new JButton("Save file");
    btnSaveFile.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      }
    });
    btnSaveFile.setBounds(484, 412, 89, 23);
    panel.add(btnSaveFile);

    JLabel lblTotalAmount = new JLabel("Total amount");
    lblTotalAmount.setBounds(401, 372, 73, 14);
    panel.add(lblTotalAmount);

    JComboBox dbAccountType = new JComboBox();
    dbAccountType.setBounds(163, 57, 61, 20);
    panel.add(dbAccountType);

    // Initialize TODO
    try {
      JAXBContext jc = JAXBContext.newInstance("se.dids.paysup.backendconfiguration");
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      Configuration configuration = (Configuration) unmarshaller.unmarshal(new File("schemas/SE_Svenska_Handelsbanken_AB.xml"));
      System.out.println(configuration.getName());
      List accountTypeList = configuration.getAccountTypes().getAccountType();
      for (Iterator iter = accountTypeList.iterator(); iter.hasNext();) {
        String accountType = (String) iter.next();
        dbAccountType.addItem(accountType);
      }

      System.out.println(new File(".").getCanonicalPath());

      // Read in model.SQL (the DDL) into string
      String ddl = readFileAsString("doc/model.SQL");


      SQLite.setLibraryPath("target/lib");
      SQLiteConnection db = new SQLiteConnection(new File("data/testa.db"));
      db.open(true);

      db.exec(ddl);
      /*
      SQLiteStatement st = db.prepare("SELECT Name FROM AccountTypes");
      try {
      while (st.step()) {
      System.out.println(st.columnString(0));
      }
      } finally {
      st.dispose();
      }

       */
      db.dispose();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  private static String readFileAsString(String filePath) throws java.io.IOException {
    byte[] buffer = new byte[(int) new File(filePath).length()];
    BufferedInputStream f = null;
    try {
      f = new BufferedInputStream(new FileInputStream(filePath));
      f.read(buffer);
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
