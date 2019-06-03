package alame.tp2;

import alame.tp3.Taux;
import alame.tp3.Graphe;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class Convertisseur extends JFrame implements ActionListener {
    // le taux de conversion.

    protected JLabel tauxL;//permet l'affichage du taux de conversion
    protected JTextField euro;//contient la valeur � convertir
    protected JTextField dollar;//contient la valeur convertie

    protected JPanel pListe = new JPanel();
    protected JComboBox liste = new JComboBox();
    protected JLabel lListe = new JLabel("Monnaie");
    protected JButton bt;
    JLabel image;
    ImageIcon[] images = new ImageIcon[10];

    public Convertisseur(){

        // appel du constructeur de la superclasse JFrame
        super("Convertisseur 1.0");

        //--------------------------------------------------------------------
        //                 Les composants graphiques
        // -------------------------------------------------------------------
        // récupération de l'objet ContentPane
        Container contentPane = this.getContentPane();

        // changement de son layout. Son layout par défaut (un BorderLayout) est
        // remplacé par un FlowLayout
        contentPane.setLayout(new BorderLayout());


        JPanel pNord=new JPanel();
        //  JLabel et  JTextField pour la valeur en euros
        JPanel pEuro =new JPanel(new BorderLayout());
        pEuro.add(new JLabel("Euro"),BorderLayout.NORTH);
        pEuro.add(euro = new JTextField(10),BorderLayout.SOUTH);
        pNord.add(pEuro);

        //  JLabel et  JTextField pour la valeur en euros
        JPanel pDollar =new JPanel(new BorderLayout());
        pDollar.add(new JLabel("Dollar"),BorderLayout.NORTH);
        pDollar.add(dollar = new JTextField(10),BorderLayout.SOUTH);
        dollar.setEditable(false);
        pNord.add(pDollar);
        contentPane.add(pNord,BorderLayout.NORTH);

        JPanel pTaux =new JPanel();
        tauxL = new JLabel("taux de conversion : 1 euro = "+ Currency.formatte(Taux.getValue(),5, Currency.USD));
        pTaux.add(tauxL);
        contentPane.add(pTaux,BorderLayout.CENTER);

        JPanel pSud =new JPanel();
        bt = new JButton(" CONVERTIR ");
        pSud.add(bt);
        contentPane.add(pSud,BorderLayout.SOUTH);

        //--------------------------------------------------------------------------
        // enrigistrement des "Listeners" pour g�rer l'interaction.

        bt.addActionListener(this);

        setJMenuBar(new MenuConvertisseur(this));

        // ajustement de la taille de la fen�tre en fonction des composants qu'elle
        // contient et des layout utilisés.
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // la fenêre n'est pas redimensionnable par l'utilisateur
        //this.setResizable(false);

    }

    /**
     * convertit une valeur en lui appliquant le taux de conversion.
     * @param v valeur � convertir.
     */
    public double convertir(double v,int k) {
        switch(k){
            case 0: Taux.setMonnaie(Taux.EUR_USD);
                break;
            case 1: Taux.setMonnaie(Taux.EUR_CAD);
                break;
            case 2: Taux.setMonnaie(Taux.EUR_CNY);
                break;
            case 3: Taux.setMonnaie(Taux.EUR_JPY);
                break;
            case 4: Taux.setMonnaie(Taux.EUR_INR);
                break;
            case 5: Taux.setMonnaie(Taux.EUR_GBP);
                break;
            case 6: Taux.setMonnaie(Taux.EUR_CHF);
                break;
            default: Taux.setValue(1);break;

        }
        image.setIcon(images[k]);
        tauxL.setText("1 euor = "+ Currency.formatte(Taux.getValue(),5, Currency.USD));
        return v * Taux.getValue();
    }

    /**
     * fixe le taux de conversion et l'affiche dans le textfield pr�vu � cet effet.
     * @param d le nouveau taux de conversion.
     */
    public void fixerTaux(double d) {
        Taux.setValue(d);
        tauxL.setText("1 euor = "+ Currency.formatte(Taux.getValue(),5, Currency.USD));
    }


    //---------------- la m�thode de l'interface ActionListener ---------------
    // lorsque le bouton a �t� activ� r�cup�re la cha�ne contenue dans le
    // JTextFiel vIn, la convertit en un double et l'affiche dans le JTextField
    // vOut. Au cas o� la cha�ne contenue dans vIn ne repr�sente pas un r�el,
    // un panneau d'alerte avec un message d'erreur le signale � l'utilisateur.
    public void actionPerformed(ActionEvent e) {
        try {
            String strEuros = euro.getText().replace(',','.');

            double val = Double.parseDouble(strEuros);
            System.out.println(val);
            int item = liste.getSelectedIndex();
            dollar.setText(""+ Currency.formatte(convertir(val,item),2, Currency.USD));
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Entrez un nombre !!!",
                    "alert",  JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        // création de la fenêtre
        Convertisseur c =  new Convertisseur();
        // affichage de la fen�tre
        c.setVisible(true);
    }
}// Convertisseur








