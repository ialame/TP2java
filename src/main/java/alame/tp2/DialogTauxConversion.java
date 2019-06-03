package alame.tp2;


import alame.tp3.Taux;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;
import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// JDialog permettant de fixer le taux de conversion
public class DialogTauxConversion extends JDialog implements ActionListener {

    private Convertisseur conv;//l'objet convertisseur auquel est associ� ce Dialog
    private JTextField taux; //le TextField pour la saisie du taux
    private JButton btV; // le bouton pour la validation du taux
    private JButton btA;// le bouton pour  annulation


    //constructeur du dialogue pour fixer le taux.
    public DialogTauxConversion(Convertisseur c) {

        super(c,"TAUX DE CONVERSION",true); // dialog modal centr�e sur le Convertisseur c

        conv = c;

        // mise en place des composants graphique du dialogue
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Taux de change"));
        contentPane.add(taux = new JTextField(5));
        taux.setText(""+ Taux.getValue());
        contentPane.add(btV = new JButton("Valider"));
        contentPane.add(btA = new JButton("Annuler"));
        btV.addActionListener(this);
        btA.addActionListener(this);

        this.setResizable(false);
        this.pack();

    }

    // methode invoqu� lorsque l'on clique sur l'un des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btV){
            try {
                double val = Double.parseDouble(taux.getText());
                conv.fixerTaux(val);
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