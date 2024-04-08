package vueGraphique;

import modele.Carte;
import modele.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class VueSokoban extends JFrame implements KeyListener {
    private Carte carte;
    private JPanel panelCarte;
    private BufferedImage imgRobotH;
    private BufferedImage imgRobotB;
    private BufferedImage imgRobotG;
    private BufferedImage imgRobotD;
    private BufferedImage imgMur;
    private BufferedImage imgSol;
    private BufferedImage imgCaisse1;
    private BufferedImage imgCaisse2;
    private BufferedImage imgDestination;

    public VueSokoban(Carte carte) {
        this.carte = carte;

        setTitle("DHAINAUT Alexandre - Sokoban");
        int hauteur = (carte.getHauteur() * 30)+40;
        int largeur = (carte.getLargeur() * 30)+17;
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Charger les images
        try {
            imgRobotH = ImageIO.read(new File("img/Haut.gif"));
            imgRobotB = ImageIO.read(new File("img/Bas.gif"));
            imgRobotG = ImageIO.read(new File("img/Gauche.gif"));
            imgRobotD = ImageIO.read(new File("img/Droite.gif"));
            imgMur = ImageIO.read(new File("img/mur.gif"));
            imgSol = ImageIO.read(new File("img/sol.gif"));
            imgCaisse1 = ImageIO.read(new File("img/caisse1.gif"));
            imgCaisse2 = ImageIO.read(new File("img/caisse2.gif"));
            imgDestination = ImageIO.read(new File("img/but.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Création du panel pour afficher la carte
        panelCarte = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerCarte(g);
            }
        };
        getContentPane().add(panelCarte);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void dessinerCarte(Graphics g) {
        int cellSize = 30; // Taille d'une cellule en pixels

        for (int i = 0; i < carte.elements.length; i++) {
            for (int j = 0; j < carte.elements[i].length; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                // Dessiner l'élément à la position (i, j)
                char symbole = carte.elements[i][j].getSymbole();
                BufferedImage img = null;
                switch (symbole) {
                    case '/':
                        img = imgSol;
                        break;
                    case '#':
                        img = imgMur;
                        break;
                    case '.':
                        img = imgDestination;
                        break;
                    case '$':
                        img = imgCaisse1;
                        break;
                    case '*':
                        img = imgCaisse2;
                        break;
                    case '+':
                        img = imgRobotH;
                        break;
                    case '@':
                        Direction direction = carte.getDirectionRobot(i, j);
                        //System.out.println(direction);
                        switch (direction) {
                            case HAUT:
                                img = imgRobotH;
                                break;
                            case BAS:
                                img = imgRobotB;
                                break;
                            case GAUCHE:
                                img = imgRobotG;
                                break;
                            case DROITE:
                                img = imgRobotD;
                                break;
                        }
                    default:
                        break;
                }
                if (img != null) {
                    g.drawImage(img, x, y, cellSize, cellSize, null);
                }
            }
        }
    }

    // Méthodes de l'interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                carte.deplacerRobot(Direction.GAUCHE);
                break;
            case KeyEvent.VK_RIGHT:
                carte.deplacerRobot(Direction.DROITE);
                break;
            case KeyEvent.VK_UP:
                carte.deplacerRobot(Direction.HAUT);
                break;
            case KeyEvent.VK_DOWN:
                carte.deplacerRobot(Direction.BAS);
                break;
            default:
                break;
        }
        panelCarte.repaint();
        if(carte.finDePartie()==true){
            //System.out.println(true);
            FinPartieDialog fin = new FinPartieDialog(this, "La partie est terminée !");
            fin.setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
