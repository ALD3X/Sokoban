package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lecture {
    private List<String> lignes;
    private int nbLignes;
    private int tailleLignes;

    /**
     * Classe pour lire le fichier contenant la carte du jeu
     * @param Fichier Nom du fichier à charger
     * @throws IOException En cas d'erreur d'entrée/sortie
     */
    public Lecture(String Fichier) throws IOException {
        lignes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(Fichier));

        String ligne;
        while ((ligne = reader.readLine()) != null) {
            lignes.add(ligne);
        }

        reader.close();

        nbLignes = lignes.size();
        tailleLignes = lignes.get(0).length();
    }

    /**
     * Obtenir les lignes de la carte
     * @return Les lignes de la carte
     */
    public List<String> getLignes() {
        return lignes;
    }

    /**
     * Obtenir le nombre de lignes de la carte
     * @return Le nombre de lignes de la carte
     */
    public int getNbLignes() {
        return nbLignes;
    }

    /**
     * Obtenir la taille des lignes de la carte
     * @return La taille des lignes de la carte
     */
    public int getTailleLignes() {
        return tailleLignes;
    }
}
