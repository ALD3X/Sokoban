package modele;

public abstract class Element {
    char symbole;
    
    public char getSymbole() {
        return symbole;
    }
    
    public abstract boolean contient_Robot();
    public abstract boolean contient_Caisse();

    public enum Direction {
        HAUT(1, 0),
        BAS(-1, 0),
        GAUCHE(0, -1),
        DROITE(0, 1);
    
        private final int X;
        private final int Y;
    
        Direction(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
    
        public int geX() {
            return X;
        }
    
        public int getY() {
            return Y;
        }
    }
}