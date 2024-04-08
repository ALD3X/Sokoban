package vueGraphique;

import java.io.IOException;
import modele.Carte;

public class Sokoban {
    public static void main(String[] args) throws IOException {
        Carte carte = new Carte("map/map2.txt");
        VueSokoban vue = new VueSokoban(carte);
    }
}
