/**
 * Projet SOKOBAN L2-INFO Jean Perrin
 *
 * @author DHAINAUT Alexandre
 * @version 1.0
 */

package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Carte {
    public Element[][] elements;
    private List<Position> destinations;
    private int hauteur;
    private int largeur;
    
    /**
     * Constructeur de la classe Carte
    * @param Fichier Nom du fichier à charger
    * @throws IOException En cas d'erreur d'entrée/sortie
    */
    public Carte(String Fichier) throws IOException {

        // Lecture du fichier
        Lecture carte = new Lecture(Fichier);
        hauteur = carte.getNbLignes();
        largeur = carte.getTailleLignes();

        // Initialisation des éléments
        elements = new Element[hauteur][largeur];
        destinations = new ArrayList<>();

        // Parcourir chaque ligne du fichier
        for (int i = 0; i < hauteur; i++) {
            String ligne = carte.getLignes().get(i);
            // Parcourir chaque caractère de la ligne
            for (int j = 0; j < largeur; j++) {
                char symbole = ligne.charAt(j);
                // Initialiser les éléments en fonction du symbole
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
                        destinations.add(new Position(i, j));
                        break;
                    case '$':
                        elements[i][j] = new Caisse(i, j);
                        break;
                    case '@':
                        elements[i][j] = new Robot(i, j, Direction.DROITE);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    /**
     * Obtenir l'élément à la position spécifiée
    * @param x Coordonnée X de l'élément
    * @param y Coordonnée Y de l'élément
    * @return L'élément à la position spécifiée
    */
    public Element getElement(int x, int y) {
        return elements[x][y];
    }

    /**
     * Obtenir la hauteur de la carte
    * @return La hauteur de la carte
    */
    public int getHauteur() {
        return hauteur;
    }
    
    /**
     * Obtenir la largeur de la carte
    * @return La largeur de la carte
    */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Obtenir la direction du robot à la position spécifiée
    * @param x Coordonnée X de l'élément
    * @param y Coordonnée Y de l'élément
    * @return La direction du robot à la position spécifiée
    */
    public Direction getDirectionRobot(int x, int y) {
        return ((Robot) elements[x][y]).getDirection();
    }
    
    /**
     * Obtenir la direction du robot à la position spécifiée (version Destination)
    * @param x Coordonnée X de l'élément
    * @param y Coordonnée Y de l'élément
    * @return La direction du robot à la position spécifiée (version Destination)
    */
    public Direction getDirectionRobot_Dest(int x, int y) {
        return ((Robot_Dest) elements[x][y]).getDirection();
    }

    /**
     * Déplacer le robot dans une direction spécifiée
    * @param direction La direction dans laquelle déplacer le robot
    */
    public void deplacerRobot(Direction direction) {
        // Trouver la position actuelle du robot
        int x = -1;
        int y = -1;
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if (elements[i][j].contient_Robot()) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        
        // Vérifier si le déplacement est possible
        if (x != -1 && y != -1) {
            int nX = x + direction.getX();
            int nY = y + direction.getY();
            if (nX >= 0 && nX < elements.length && nY >= 0 && nY < elements[0].length) {
                if (elements[nX][nY].getSymbole() == ' ' || elements[nX][nY].getSymbole() == '.') {
                    if (elements[x][y].getSymbole() == '+') {
                        elements[x][y] = new Destination();
                    } else {
                        elements[x][y] = new Sol();
                    }
                    if (elements[nX][nY].getSymbole() == '.') {
                        elements[nX][nY] = new Robot_Dest(nX, nY, direction);
                    } else {
                        elements[nX][nY] = new Robot(nX, nY, direction);
                    }
                }
                if (elements[nX][nY].contient_Caisse()) {
                    int nnX = nX + direction.getX();
                    int nnY = nY + direction.getY();
                    if (nnX >= 0 && nnX < elements.length && nnY >= 0 && nnY < elements[0].length) {
                        if (elements[nnX][nnY].getSymbole() == ' ' || elements[nnX][nnY].getSymbole() == '.') {

                            if (elements[x][y].getSymbole() == '+') {
                                elements[x][y] = new Destination();
                            }else{
                                elements[x][y] = new Sol();
                            }

                            if (elements[nX][nY].getSymbole() == '*') {
                                elements[nX][nY] = new Robot_Dest(nX, nY, direction);
                            }else{
                                elements[nX][nY] = new Robot(nX, nY, direction);
                            }
                            
                            if(elements[nnX][nnY].getSymbole() == '.'){
                                elements[nnX][nnY] = new Caisse_Dest(nnX, nnY);
                            }else{
                                elements[nnX][nnY] = new Caisse(nnX, nnY);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Convertir la carte en chaîne de caractères
    * @return La représentation de la carte en chaîne de caractères
    */
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

    // Classe interne pour gérer les positions des destinations
    private class Position {
        private int x;
        private int y;
        
        // Constructeur de la classe Position
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        // Obtenir la coordonnée X
        public int getX() {
            return x;
        }
        
        // Obtenir la coordonnée Y
        public int getY() {
            return y;
        }
    }

    /**
     * Vérifier si la partie est terminée
    * @return true si la partie est terminée, sinon false
    */
    public boolean finDePartie() {
        for (int i = 0; i < destinations.size(); i++) {
            Position destination = destinations.get(i);
            int x = destination.getX();
            int y = destination.getY();
            if (!(elements[x][y].contient_Caisse())) {
                return false;
            }
        }
        return true;
    }
}
