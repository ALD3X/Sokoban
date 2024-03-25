package vueTexte;
import java.io.IOException;

import modele.Carte;

public class SokobanTexte {
    public static void main(String[] args) throws IOException {
        Carte carte = new Carte("map/map1.txt");
        System.out.println(carte.toString());
    }
}
