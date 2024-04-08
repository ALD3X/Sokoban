package vueTexte;
import java.io.IOException;

import modele.Carte;

public class SokobanTexte {
    public static void main(String[] args) throws IOException {
        Carte carte = new Carte("map/map2.txt");
        ModeTexte modeTexte = new ModeTexte(carte);
        modeTexte.demarrerPartie();
    }
}