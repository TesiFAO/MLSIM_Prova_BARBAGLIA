package it.unimarconi.commons;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Double> toList(double[] a) {
        ArrayList<Double> l = new ArrayList<Double>();
        for (int i = 0 ; i < a.length ; i++)
            l.add(a[i]);
        return l;
    }

}