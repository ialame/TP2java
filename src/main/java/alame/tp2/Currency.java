package alame.tp2;

import java.text.NumberFormat;

/**
 * Currency.java<BR>
 * Construit une chaîne formatée à partir d'un réel représentant une somme
 * dans une monnaie donnée.
 *
 */
public class Currency {
    // Le symbole pour les euros.
    public static final String EURO = "" + (char) (8364);
    // le symbole pour les dollars US
    public static final String USD = "$US";
    // le CAD
    public static final String CAD = "$CA";
    // le symbole pour les Livres Sterling
    public static final String POUNDS = "£";
    // le Yen
    public static final String YEN = "¥";
    // le Roupie
    public static final String ROUPIE = "₹";
    // le Youan
    public static final String YOUAN = "元";
    // le CHF
    public static final String CHF = "₣";
    /**
     * formatte une valeur réelle réprésentant une somme dans une monnaie donnée.
     * Retourne la chaine représentant le nombre réel arrondi avec un nombre de
     * décimales fixé suivant du symbole de la monnaie souhaitée.<BR>
     * Par exemple pour formmatter une somme en $. Si 10.4586 est le montant de
     * la somme, la chaîne formatée sera : "10.46 $".
     *
     * @param v  la valeur à formatter
     * @param nbdec le nombre de décimales souhaitées
     * @param monnaieSymb La chaîne représentant le symbole de la monnaie
     * @return la chaîne représentant la valeur formattée.
     */
    public static String formatte(double v, int nbdec, String monnaieSymb) {
        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMaximumFractionDigits(nbdec) ;
        fmt.setMinimumFractionDigits(nbdec) ;
        return fmt.format(v) + " " + monnaieSymb;
    }

} // Currency
