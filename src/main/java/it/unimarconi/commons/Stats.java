package it.unimarconi.commons;

import java.util.ArrayList;

public class Stats {

    public static double media(ArrayList<Double> l) {
        double sum = 0.0;
        for (Double d : l)
            sum += d;
        return sum / l.size();
    }

    public static double sd(ArrayList<Double> l) {
        double avg = media(l);
        double sum = 0.0;
        for (Double d : l)
            sum += Math.pow(d - avg, 2.0);
        return sum / l.size();
    }

}