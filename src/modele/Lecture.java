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

    public List<String> getLignes() {
        return lignes;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getTailleLignes() {
        return tailleLignes;
    }
}
