package alame.tp3;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Convertisseur extends JFrame implements ActionListener {
    // le taux de conversion.

    protected JLabel tauxL;//permet l'affichage du taux de conversion
    protected JTextField euro;//contient la valeur � convertir
    protected JTextField dollar;//contient la valeur convertie

    protected JPanel pListe = new JPanel();
    protected JComboBox liste = new JComboBox();
    protected JLabel lListe = new JLabel("Monnaie");
    protected Graphe graphe;
    JLabel image;
    ImageIcon[] images = new ImageIcon[10];

    public Convertisseur(){

        // appel du constructeur de la superclasse JFrame
        super("Convertisseur 2.0");

        //--------------------------------------------------------------------
        //                 Les composants graphiques
        // -------------------------------------------------------------------
        // r�cup�ration de l'objet ContentPane
        Container contentPane = this.getContentPane();

        // changement de son layout. Son layout par d�faut (un BorderLayout) est
        // remplac� par un FlowLayout
        contentPane.setLayout(new BorderLayout());


        JPanel pNord=new JPanel();
        JLabel titre = new JLabel("Convertisseur universel  ");
        Font f=new Font("AR BLANCA",Font.BOLD,26);

        titre.setFont(f);
        pNord.add(titre);
        //  JLabel et  JTextField pour la valeur en euros
        JPanel pEuro =new JPanel(new BorderLayout());
        pEuro.add(new JLabel("Euro"),BorderLayout.NORTH);
        pEuro.add(euro = new JTextField(10),BorderLayout.SOUTH);
        pNord.add(pEuro);
        pNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // � l'est
        JPanel pEst=new JPanel(new BorderLayout());

        contentPane.add(pEst,BorderLayout.EAST);

        //  JLabel et  JTextField pour la valeur en dollar
        JPanel pListe =new JPanel(new BorderLayout());

        pListe.add(lListe,BorderLayout.NORTH);
        pListe.add(liste,BorderLayout.SOUTH);
        pNord.add(pListe);

        liste.setPreferredSize(new Dimension(100, 20));
        liste.addItem("Dollar");
        liste.addItem("Dollar Canadien");
        liste.addItem("Yuan");
        liste.addItem("Yen");
        liste.addItem("Roupie");
        liste.addItem("Livre");
        liste.addItem("Franc Suisse");



        contentPane.add(pNord,BorderLayout.NORTH);

        // Cr�ation et ajout du JButton d�clenchant la conversion
        JButton bt = new JButton("CONVERTIR");
        JPanel pBouton =new JPanel();
        pBouton.add(bt);
        contentPane.add(pBouton,BorderLayout.SOUTH);

        //  JLabel et  JTextField pour la valeur en dollar
        JPanel pCentre =new JPanel(new BorderLayout());
        JPanel pResultat =new JPanel();
        JLabel lResultat = new JLabel("Valeur en Dollars");
        dollar = new JTextField(10);
        pResultat.add(lResultat);
        pResultat.add(dollar);
        pResultat.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        //pResultat.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        pEst.add(pResultat,BorderLayout.NORTH);
        graphe =new Graphe();
        pEst.add(graphe,BorderLayout.CENTER);
        //Les images

        images[0] = new ImageIcon("src/Visuel_Amerique_Est.jpg");
        images[1] = new ImageIcon("src/canada.jpg");
        images[2] = new ImageIcon("src/chine.jpg");
        images[3] = new ImageIcon("src/japon.jpg");
        images[4] = new ImageIcon("src/inde.jpeg");
        images[5] = new ImageIcon("src/GB.jpeg");
        images[6] = new ImageIcon("src/suisse.jpg");

        image = new JLabel( images[0]);
        image.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        image.setPreferredSize(new Dimension(200,200));

        pCentre.add(image,BorderLayout.CENTER);
        JPanel pTaux =new JPanel();
        tauxL = new JLabel("taux de conversion : 1 euro = "+ Currency.formatte(Taux.getValue(),5, Currency.USD));
        pTaux.add(tauxL);

        pCentre.add(pTaux,BorderLayout.SOUTH);

        dollar.setEditable(false);

        contentPane.add(pCentre,BorderLayout.CENTER);

        //--------------------------------------------------------------------------
        // enrigistrement des "Listeners" pour g�rer l'interaction.

        //this.addWindowListener(this);
        bt.addActionListener(this);
        liste.addActionListener(this);
        this.setJMenuBar(new MenuConvertisseur(this));

        // ajustement de la taille de la fen�tre en fonction des composants qu'elle
        // contient et des layout utilis�s.
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // la fen�re n'est pas redimensionnable par l'utilisateur
        //this.setResizable(false);

    }

    /**
     * convertit une valeur en lui appliquant le taux de conversion.
     * @param v valeur � convertir.
     */
    public double convertir(double v,int k) {
        switch(k){
            case 0: Taux.setMonnaie(Taux.EUR_USD);
                graphe.repaint();
                break;
            case 1: Taux.setMonnaie(Taux.EUR_CAD);
                graphe.repaint();
                break;
            case 2: Taux.setMonnaie(Taux.EUR_CNY);
                graphe.repaint();
                break;
            case 3: Taux.setMonnaie(Taux.EUR_JPY);
                graphe.repaint();
                break;
            case 4: Taux.setMonnaie(Taux.EUR_INR);
                graphe.repaint();
                break;
            case 5: Taux.setMonnaie(Taux.EUR_GBP);
                graphe.repaint();
                break;
            case 6: Taux.setMonnaie(Taux.EUR_CHF);
                graphe.repaint();
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








