package modele;

class Robot extends Element {
    private int x;
    private int y;
    private Direction direction;

    /**
     * Constructeur de la classe Robot
     * @param x Position X du robot
     * @param y Position Y du robot
     * @param direction Direction du robot
     */
    public Robot(int x, int y, Direction direction) {
        this.symbole = '@';
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    @Override
    public boolean contient_Robot() {
        return true;
    }

    @Override
    public boolean contient_Caisse() {
        return false;
    }

    /**
     * Obtenir la position X du robot
     * @return La position X du robot
     */
    public int getX() {
        return x;
    }

    /**
     * Définir la position X du robot
     * @param x La nouvelle position X du robot
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtenir la position Y du robot
     * @return La position Y du robot
     */
    public int getY() {
        return y;
    }

    /**
     * Définir la position Y du robot
     * @param y La nouvelle position Y du robot
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtenir la direction du robot
     * @return La direction du robot
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Définir la direction du robot
     * @param direction La nouvelle direction du robot
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
