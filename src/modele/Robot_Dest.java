package modele;

class Robot_Dest extends Element {
    private int x;
    private int y;
    private Direction direction;

    public Robot_Dest(int x, int y, Direction direction) {
        this.symbole = '+';
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
