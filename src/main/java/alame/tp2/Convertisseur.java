package alame.tp2;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Convertisseur extends JFrame implements ActionListener {
    // le taux de conversion.
    protected double taux = 1.3563;
    protected JLabel lblTaux;//permet l'affichage du taux de conversion
    protected JTextField tfEuro;//contient la valeur � convertir
    protected JTextField tfDollar;//contient la valeur convertie

    protected JComboBox liste = new JComboBox();
    protected JButton bt;

    /**
     * Le constructeur
     */
    public Convertisseur(){
        super("Convertisseur 1.0");
        // récupération de l'objet ContentPane
        Container contentPane = this.getContentPane();
        //       N O R D
        JPanel pNord=new JPanel();
        //  JLabel et  JTextField pour la valeur en euros
        JPanel pEuro =new JPanel(new BorderLayout());
        pEuro.add(new JLabel("Euro"),BorderLayout.NORTH);
        pEuro.add(tfEuro = new JTextField(10),BorderLayout.SOUTH);
        pNord.add(pEuro);

        //  JLabel et  JTextField pour la valeur en euros
        JPanel pDollar =new JPanel(new BorderLayout());
        pDollar.add(new JLabel("Dollar"),BorderLayout.NORTH);
        pDollar.add(tfDollar = new JTextField(10),BorderLayout.SOUTH);
        tfDollar.setEditable(false);
        pNord.add(pDollar);
        contentPane.add(pNord,BorderLayout.NORTH);
        //       C E N T R E
        JPanel pTaux =new JPanel();
        lblTaux = new JLabel("taux de conversion : 1 euro = "+ Currency.formatte(taux,5, Currency.USD));
        pTaux.add(lblTaux);
        contentPane.add(pTaux,BorderLayout.CENTER);
        //       S U D
        JPanel pSud =new JPanel();
        bt = new JButton(" CONVERTIR ");
        pSud.add(bt);
        contentPane.add(pSud,BorderLayout.SOUTH);

        // enrigistrement des "Listeners" pour gérer l'interaction.
        bt.addActionListener(this);

        setJMenuBar(new MenuConvertisseur(this));

        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // la fenêre n'est pas redimensionnable par l'utilisateur
        this.setResizable(false);
        // affichage de la fenêtre
        setVisible(true);

    }

    /**
     * la méthode de l'interface ActionListener
     */
    public void actionPerformed(ActionEvent e) {
        try {
            String strEuros = tfEuro.getText().replace(',','.');
            double val = Double.parseDouble(strEuros);
            tfDollar.setText(""+ Currency.formatte(val*taux,2, Currency.USD));
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Entrez un nombre !!!",
                    "alert",  JOptionPane.ERROR_MESSAGE);
        }
    }
    public double getTaux() {
       return this.taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }

    /**
     * La méthode M A I N
     * @param args
     */
    public static void main(String[] args) {
        new Convertisseur();
    }
}// Convertisseur








