package modele;

public enum Direction {
    HAUT(-1, 0),
    BAS(1, 0),
    GAUCHE(0, -1),
    DROITE(0, 1);

    private final int x;
    private final int y;

    /**
     * Constructeur de l'enum Direction
     * @param x Déplacement en X
     * @param y Déplacement en Y
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtenir le déplacement en X
     * @return Le déplacement en X
     */
    public int getX() {
        return x;
    }

    /**
     * Obtenir le déplacement en Y
     * @return Le déplacement en Y
     */
    public int getY() {
        return y;
    }
}
