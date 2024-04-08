package modele;

public abstract class Element {
    char symbole;
    
    public char getSymbole() {
        return symbole;
    }
    
    public abstract boolean contient_Robot();
    public abstract boolean contient_Caisse();
}