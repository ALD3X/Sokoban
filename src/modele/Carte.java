package modele;

import java.io.IOException;

public class Carte {
    public Element[][] elements;
    
    public Carte(String Fichier) throws IOException {
        Lecture carte = new Lecture(Fichier);
        int hauteur = carte.getNbLignes();
        int largeur = carte.getTailleLignes();
        elements = new Element[hauteur][largeur];
        
        for (int i = 0; i < hauteur; i++) {
            String ligne = carte.getLignes().get(i);
            for (int j = 0; j < largeur; j++) {
                char symbole = ligne.charAt(j);
                switch (symbole) {
                    case '/':
                        elements[i][j] = new Vide();
                        break;
                    case '#':
                        elements[i][j] = new Mur();
                        break;
                    case ' ':
                        elements[i][j] = new Sol();
                        break;
                    case '.':
                        elements[i][j] = new Destination();
                        break;
                    case '$':
                        elements[i][j] = new Caisse();
                        break;
                    case '@':
                        elements[i][j] = new Robot();
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public Element getElement(int x, int y) {
        return elements[x][y];
    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                map.append(elements[i][j].getSymbole());
            }
            map.append("\n");
        }
        return map.toString();
    }
}
