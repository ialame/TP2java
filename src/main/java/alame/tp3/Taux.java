package alame.tp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class Taux {
    public static final String  EUR_USD = "euro-dollar-EUR-USD";  // USA
    public static final String  EUR_CAD = "euro-dollarcanadien-EUR-CAD"; //   canada
    public static final String  EUR_CNY = "euro-yuanrenminbi-EUR-CNY"; //        chine
    public static final String  EUR_JPY = "euro-yen-EUR-JPY"; //    japon
    public static final String  EUR_INR = "euro-roupie-EUR-INR"; //      inde
    public static final String  EUR_GBP = "euro-livresterling-EUR-GBP"; //      royaume-uni
    public static final String  EUR_CHF = "euro-francsuisse-EUR-CHF"; //     suisse
    static ArrayList<String> J = new ArrayList<String>();
    static ArrayList<Double> T ;
    static String monnaie=EUR_JPY;// par défaut $
    protected static double value = 1.3563;  // 1 euro = 1,3563 dollars

    public static String getMonnaie() {
        return monnaie;
    }

    public static void setMonnaie(String monnaie) {
        Taux.monnaie = monnaie;
    }

    public ArrayList<Double> extraireTaux(){
        try {
            //taux-de-change-euro-dollar-EUR-USD
            //taux-de-change-euro-dollarcanadien-EUR-CAD/   canada
            //taux-de-change-euro-yuanrenminbi-EUR-CNY/     chine
            //https://www.boursorama.com/bourse/devises/taux-de-change-euro-yen-EUR-JPY/  japon
            //https://www.boursorama.com/bourse/devises/taux-de-change-euro-roupie-EUR-INR/   inde
            // https://www.boursorama.com/bourse/devises/taux-de-change-euro-livresterling-EUR-GBP/   royaume-uni
            // https://www.boursorama.com/bourse/devises/taux-de-change-euro-francsuisse-EUR-CHF/  suisse
            //Document doc = Jsoup.connect("http://www.boursorama.com/bourse/devises/taux-de-change").get();
            Document doc = Jsoup.connect("https://www.boursorama.com/bourse/devises/taux-de-change-"+monnaie).get();

            Element TH = doc.select("tr.c-table__row").first();

            Iterator<Element> ths = TH.select("th").iterator();


            Element th=ths.next();


            while(ths.hasNext()){
                th = ths.next();
                J.add(th.text());
            }
            Element TBODY = doc.select("tbody.c-table__body").first();
            Iterator<Element> tds = TBODY.select("tr").first().children().iterator();
            Element td=tds.next();
            T = new ArrayList<Double>();
            while(tds.hasNext()){
                td = tds.next();
                T.add(Double.parseDouble(td.text()));
            }
            Element div = doc.select("div.c-faceplate__info").first();
            String t = div.select("span.c-instrument--last").text();
            T.add(Double.parseDouble(t));
            /*System.out.println(t);
            for(int i=0;i<T.size();i++)
            System.out.println(J.get(i)+" => "+T.get(i));*/


        } catch (IOException e) {
            e.printStackTrace();
        }
        return T;
    }

    public static double getValue() {
        return value;
    }

    public static void setValue(double value1) {
        value = value1;
    }

    public static void main(String[] args) throws IOException {
        //extraireTaux();
    }

}
