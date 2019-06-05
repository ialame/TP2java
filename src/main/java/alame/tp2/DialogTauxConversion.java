package alame.tp2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;

// JDialog permettant de fixer le taux de conversion
public class DialogTauxConversion extends JDialog implements ActionListener {

    private Convertisseur conv; //l'objet convertisseur auquel est associ� ce Dialog
    private JTextField tfTaux;  //le TextField pour la saisie du taux
    private JButton btV;  // le bouton pour la validation du taux
    private JButton btA; // le bouton pour  annulation
    private JButton btI; // le bouton internet


    //constructeur du dialogue pour fixer le taux.
    public DialogTauxConversion(Convertisseur c) {

        super(c,"TAUX DE CONVERSION",true); // dialog modal centr�e sur le Convertisseur c

        conv = c;

        // mise en place des composants graphique du dialogue
        Container contentPane = this.getContentPane();
        //contentPane.setLayout(new FlowLayout());
        JPanel pNord = new JPanel();
        JPanel pSud = new JPanel();
        pNord.add(new JLabel("Taux de change"));
        pNord.add(tfTaux = new JTextField(5));

        pNord.add(btI=new JButton("Internet"));
        tfTaux.setText(""+ conv.getTaux());
        pSud.add(btV = new JButton("Valider"));
        pSud.add(btA = new JButton("Annuler"));
        contentPane.add(pNord, BorderLayout.NORTH);
        contentPane.add(pSud, BorderLayout.SOUTH);
        btV.addActionListener(this);
        btA.addActionListener(this);
        btI.addActionListener(this);
        this.setResizable(false);
        this.pack();

    }
    public void downloadTaux(){
        try{
            Document doc = Jsoup.connect("http://www.boursorama.com/bourse/devises/taux-de-change-euro-dollar-EUR-USD").get();
            Element div = doc.select("div.c-faceplate__info").first();
            String t = div.select("span.c-instrument--last").text();
            tfTaux.setText(t);// = Double.parseDouble(t);
        }catch (Exception ex){

        }

    }
    // methode invoqu� lorsque l'on clique sur l'un des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btI){
            downloadTaux();
        }else if (e.getSource() == btV){
            try {
                double val = Double.parseDouble(tfTaux.getText());
                conv.setTaux(val);
                conv.lblTaux.setText("1 euor = "+ Currency.formatte(val,5, Currency.USD));
                this.setVisible(false);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Entrez un nombre !!!",
                        "alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            this.setVisible(false);
    }

}// DialogTauxConversion