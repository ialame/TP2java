package alame.tp3;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graphe extends JPanel {
    Graphe(){
        setPreferredSize(new Dimension(250,200));
    }
    /////////////////////  LIRE FICHIER   //////////////////////////////
    /**
     * Lecture  des données dans un fichier
     */
    public static String lireFichier(String fichier){
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader buff = new BufferedReader(new FileReader(fichier));
            try {
                String line;
                while ((line = buff.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            finally {
                buff.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void paint(Graphics g1) {
        //
        Graphics2D  g = (Graphics2D) g1;
        BasicStroke line2 = new BasicStroke(2.0f);
        BasicStroke line1 = new BasicStroke(1.0f);
        BasicStroke line05 = new BasicStroke(0.5f);
        Taux html = new Taux();
        ArrayList<Double> T=html.extraireTaux();
        double xmin=0, xmax=6;
        double ymin = Double.MAX_VALUE;
        double ymax =Double.MIN_VALUE;
        for(double t : T){
            if (t<ymin)ymin=t;
            if(t>ymax)ymax=t;
        }
        int w=this.getWidth();
        int h = this.getHeight();
        double alpha = 0.05*(xmax-xmin);
        double beta = 0.05*(ymax-ymin);
        double A=w/(xmax-xmin+2*alpha),B=-A*(xmin-alpha);
        double C=-h/(ymax-ymin+2*beta),D=h-C*(ymin-beta);
        double t=T.get(0);
        int x, xd = (int)B;
        int y, yd = (int)(C*t+D);
        for(int k=0;k<T.size();k++){
            t=T.get(k);
            x=(int)(A*k+B);
            y=(int)(C*t+D);
            g.setColor(Color.RED);
            g.setStroke(line2);
            g.drawLine(xd, yd, x, y);
            g.setStroke(line05);
            g.setColor(Color.GREEN);
            g.drawLine(0, y, w, y);
            g.setStroke(line1);
            g.setColor(Color.ORANGE);
            g.fillOval(x-4,y-4,8,8);

            xd = x; yd = y;
        }
        g.setColor(Color.BLUE);
        g.drawLine(0, h, w, h);
        g.drawLine(0, h, 0, 0);

    }

}