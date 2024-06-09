package vueGraphique;

import modele.Carte;
import modele.Direction;
import modele.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class VueSokoban extends JFrame implements KeyListener {
    private Carte carte;
    private JPanel panelCarte;
    private JPanel bouton;
    private JButton suivant;
    private JLabel compteur;
    private int total;
    private int fichiersIndex = 0;
    private int fichiersSuivant = fichiersIndex;
    private List<String> fichiers = Arrays.asList(
        "map/map0.txt",
        "map/map1.txt",
        "map/map2.txt",
        "map/map3.txt"
    );

    private BufferedImage imgRobotH;
    private BufferedImage imgRobotB;
    private BufferedImage imgRobotG;
    private BufferedImage imgRobotD;
    private BufferedImage imgMur;
    private BufferedImage imgSol;
    private BufferedImage imgCaisse1;
    private BufferedImage imgCaisse2;
    private BufferedImage imgDestination;

    private Element[][] elements2;

    /**
     * Constructeur de la classe VueSokoban
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture des images
    */
    public VueSokoban() throws IOException {
        //Init CARTE
        this.carte = new Carte(fichiers.get(fichiersIndex));
        this.elements2 = new Element[carte.getHauteur()][carte.getLargeur()];

        //Init FENETRE
        setTitle("DHAINAUT Alexandre - Sokoban");
        int hauteur = (carte.getHauteur() * 60)+75;
        int largeur = (carte.getLargeur() * 60)+17;
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Init des IMG
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

        //Affichage CARTE
        panelCarte = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerCarte(g);
            }
        };
        getContentPane().add(panelCarte);

        //COMPTEUR
        compteur = new JLabel("Nombre de déplacements : "+total);

        //Bouton QUITTER
        JButton quitter = new JButton("Quitter");
        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
            }
        });

        //Bouton RECOMMENCER
        JButton recommencer = new JButton("Recommencer");
        recommencer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    carte = new Carte(fichiers.get(fichiersIndex));
                } catch (IOException a) {
                    a.printStackTrace();
                }
                int hauteur = (carte.getHauteur() * 60)+75;
                int largeur = (carte.getLargeur() * 60)+17;
                setSize(largeur, hauteur);
                setLocationRelativeTo(null);
                total=0;
                compteur.setText("Nombre de déplacements : " + total);
                panelCarte.repaint();
                requestFocusInWindow();
            }
        });
    
        //Bouton SUIVANT
        suivant = new JButton("Suivant");
        suivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fichiersSuivant++;
                try {
                    carte = new Carte(fichiers.get(fichiersSuivant));
                } catch (IOException a) {
                    a.printStackTrace();
                }
                int hauteur = (carte.getHauteur() * 60)+55;
                int largeur = (carte.getLargeur() * 60)+17;
                setSize(largeur, hauteur);
                setLocationRelativeTo(null);
                total=0;
                compteur.setText("Nombre de déplacements : " + total);
                panelCarte.repaint();
                fichiersIndex=fichiersSuivant;
                suivant.setVisible(false);
                requestFocusInWindow();
            }
        });

        //Bouton ANNULER
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                annulerXcopie(elements2, carte.elements);
                if(total!=0){total--;}
                compteur.setText("Nombre de déplacements : " + total);
                panelCarte.repaint();
                suivant.setVisible(false);
                requestFocusInWindow();
            }
        });

        //Affichage BAS DE PAGE
        bouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bouton.add(compteur);
        bouton.add(suivant);
        bouton.add(recommencer);
        bouton.add(annuler);
        bouton.add(quitter);
        getContentPane().add(bouton, BorderLayout.SOUTH);

        // Parametre FENETRE
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        suivant.setVisible(false);
    }

    /**
     * Dessine la carte du jeu
     * @param g Objet Graphics utilisé pour dessiner
    */
    private void dessinerCarte(Graphics g) {
        int cellSize = 60;

        for (int i = 0; i < carte.elements.length; i++) {
            for (int j = 0; j < carte.elements[i].length; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                char symbole = carte.elements[i][j].getSymbole();
                BufferedImage img = null;
                switch (symbole) {
                    case ' ':
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
                        Direction directionD = carte.getDirectionRobot_Dest(i, j);
                        switch (directionD) {
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
                        break;
                    default:
                        break;
                }
                if (img != null) {
                    g.drawImage(img, x, y, cellSize, cellSize, null);
                }
            }
        }
    }

    /**
     * Annule une copie des éléments
     * @param a Première matrice d'éléments
     * @param b Deuxième matrice d'éléments
    */
    private void annulerXcopie(Element[][] a, Element[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                b[i][j] = a[i][j];
            }
        }
    }

    // Méthodes de l'interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if(carte.finDePartie() == false){
            annulerXcopie(carte.elements, elements2);
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
            total++;
            compteur.setText("Nombre de déplacements : " + total);
            panelCarte.repaint();
            if(carte.finDePartie()){
                compteur.setText("Niveau terminée en "+total+" coups !");
                suivant.setVisible(true);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
