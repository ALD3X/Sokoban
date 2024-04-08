package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Carte {
    public Element[][] elements;
    private List<Position> destinations;
    private int hauteur;
    private int largeur;
    
    public Carte(String Fichier) throws IOException {

        Lecture carte = new Lecture(Fichier);
        hauteur = carte.getNbLignes();
        largeur = carte.getTailleLignes();

        elements = new Element[hauteur][largeur];
        destinations = new ArrayList<>();

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
    
    public Element getElement(int x, int y) {
        return elements[x][y];
    }

    public int getHauteur() {
        return hauteur;
    }
    public int getLargeur() {
        return largeur;
    }

    public Direction getDirectionRobot(int x, int y) {
        return ((Robot) elements[x][y]).getDirection();
    }
    public Direction getDirectionRobot_Dest(int x, int y) {
        return ((Robot_Dest) elements[x][y]).getDirection();
    }

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

    private class Position {
        private int x;
        private int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
    }

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
