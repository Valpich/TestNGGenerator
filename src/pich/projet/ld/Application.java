package pich.projet.ld;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Application {

  private JFrame frmGnrateurDeTest;
  private JTextPane textPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Application window = new Application();
          window.frmGnrateurDeTest.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Application() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmGnrateurDeTest = new JFrame();
    frmGnrateurDeTest.setTitle("Générateur de classe TestNG");
    frmGnrateurDeTest.setSize(1000, 150);
    frmGnrateurDeTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmGnrateurDeTest.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    frmGnrateurDeTest.setLocation(dim.width / 2 - frmGnrateurDeTest.getSize().width / 2,
        dim.height / 2 - frmGnrateurDeTest.getSize().height / 2);
    JFileChooser fc;
    fc = new JFileChooser();

    JPanel panel = new JPanel();
    frmGnrateurDeTest.getContentPane().add(panel);
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[] {266, 468, 266};
    gbl_panel.rowHeights = new int[] {50, 50, 0};
    gbl_panel.columnWeights = new double[] {0.0, 0.0, 0.0};
    gbl_panel.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
    panel.setLayout(gbl_panel);

    JButton btnChoisir = new JButton("Choisir");
    btnChoisir.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(null);
        StringBuilder log = new StringBuilder("");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          try {
            log.append(file.getCanonicalPath());
          } catch (IOException e1) {
            e1.printStackTrace();
          }
          getTextPane().setText(log.toString());
        } else {
          getTextPane().setText("");
        }
      }
    });

    JTextPane txtpnVousAvezChoisi = new JTextPane();
    txtpnVousAvezChoisi.setEditable(false);
    txtpnVousAvezChoisi.setText("Vous avez choisi le fichier suivant :");
    GridBagConstraints gbc_txtpnVousAvezChoisi = new GridBagConstraints();
    gbc_txtpnVousAvezChoisi.fill = GridBagConstraints.BOTH;
    gbc_txtpnVousAvezChoisi.insets = new Insets(0, 0, 5, 5);
    gbc_txtpnVousAvezChoisi.gridx = 0;
    gbc_txtpnVousAvezChoisi.gridy = 0;
    panel.add(txtpnVousAvezChoisi, gbc_txtpnVousAvezChoisi);

    textPane = new JTextPane();
    GridBagConstraints gbc_textPane = new GridBagConstraints();
    gbc_textPane.fill = GridBagConstraints.BOTH;
    gbc_textPane.insets = new Insets(0, 0, 5, 5);
    gbc_textPane.gridx = 1;
    gbc_textPane.gridy = 0;
    panel.add(textPane, gbc_textPane);
    GridBagConstraints gbc_btnChoisir = new GridBagConstraints();
    gbc_btnChoisir.fill = GridBagConstraints.BOTH;
    gbc_btnChoisir.insets = new Insets(0, 0, 5, 0);
    gbc_btnChoisir.gridx = 2;
    gbc_btnChoisir.gridy = 0;
    panel.add(btnChoisir, gbc_btnChoisir);

    Component horizontalStrut = Box.createHorizontalStrut(20);
    GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
    gbc_horizontalStrut.fill = GridBagConstraints.BOTH;
    gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
    gbc_horizontalStrut.gridx = 0;
    gbc_horizontalStrut.gridy = 1;
    panel.add(horizontalStrut, gbc_horizontalStrut);

    JButton btnGnrer = new JButton("Générer");
    btnGnrer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ParseurJava parseur = new ParseurJava();
        new GenerateurDeTest().genererTest(getTextPane().getText(),
            parseur.recupererConstructeur(getTextPane().getText()),
            parseur.recupererMethodes(getTextPane().getText()));
      }
    });
    GridBagConstraints gbc_btnGnrer = new GridBagConstraints();
    gbc_btnGnrer.fill = GridBagConstraints.BOTH;
    gbc_btnGnrer.insets = new Insets(0, 0, 0, 5);
    gbc_btnGnrer.gridx = 1;
    gbc_btnGnrer.gridy = 1;
    panel.add(btnGnrer, gbc_btnGnrer);

    Component horizontalStrut_2 = Box.createHorizontalStrut(20);
    GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
    gbc_horizontalStrut_2.fill = GridBagConstraints.BOTH;
    gbc_horizontalStrut_2.gridx = 2;
    gbc_horizontalStrut_2.gridy = 1;
    panel.add(horizontalStrut_2, gbc_horizontalStrut_2);

    JMenuBar menuBar = new JMenuBar();
    frmGnrateurDeTest.setJMenuBar(menuBar);

    JMenu mnMenu = new JMenu("Menu");
    menuBar.add(mnMenu);

    JMenuItem mntmRienPourLinstant = new JMenuItem("Rien pour l'instant");
    mnMenu.add(mntmRienPourLinstant);

  }

  public JTextPane getTextPane() {
    return textPane;
  }

  public void setTextPane(JTextPane textPane) {
    this.textPane = textPane;
  }

}
