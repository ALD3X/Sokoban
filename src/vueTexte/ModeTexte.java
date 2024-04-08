package vueTexte;

import java.util.Map;
import java.util.HashMap;
import modele.*;

public class ModeTexte {
    private Carte carte;
    private Map<Character, Direction> touches;

    public ModeTexte(Carte carte) {
        this.carte = carte;
        touches = new HashMap<>();
        touches.put('q', Direction.GAUCHE);
        touches.put('z', Direction.HAUT);
        touches.put('d', Direction.DROITE);
        touches.put('s', Direction.BAS);
    }

    public void demarrerPartie() {
        boolean partieTerminee = false;

        while (!partieTerminee) {
            System.out.println(carte);

            System.out.println("Veuillez choisir une direction (q: gauche, z: haut, d: droite, s: bas): ");
            char choix = Outil.lireCaractere();
            Direction direction = touches.get(choix);

            if (direction != null) {
                carte.deplacerRobot(direction);
                partieTerminee = carte.finDePartie();
            } else {
                System.out.println("Direction non valide. Veuillez choisir une direction valide.");
            }
        }

        System.out.println("Félicitations, vous avez terminé la partie !");
    }
}

