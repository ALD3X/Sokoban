/**
* Projet SOKOBAN L2-INFO Jean Perrin
*
* @author DHAINAUT Alexandre
* @version 1.0
*/
package vueTexte;
import java.io.IOException;

import modele.Carte;

public class SokobanTexte {
    public static void main(String[] args) throws IOException {
        Carte carte = new Carte("map/map0.txt");
        ModeTexte modeTexte = new ModeTexte(carte);
        modeTexte.demarrerPartie();
    }
}