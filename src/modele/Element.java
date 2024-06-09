package modele;

public abstract class Element {
    char symbole;
    
    /**
     * Obtenir le symbole de l'élément
     * @return Le symbole de l'élément
     */
    public char getSymbole() {
        return symbole;
    }
    
    /**
     * Vérifier si l'élément contient un robot
     * @return true si l'élément contient un robot, sinon false
     */
    public abstract boolean contient_Robot();
    
    /**
     * Vérifier si l'élément contient une caisse
     * @return true si l'élément contient une caisse, sinon false
     */
    public abstract boolean contient_Caisse();
}
