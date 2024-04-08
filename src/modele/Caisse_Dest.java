package modele;

class Caisse_Dest extends Element {
    private int x;
    private int y;

    public Caisse_Dest(int x, int y) {
        this.symbole = '*';
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean contient_Robot() {
        return false;
    }

    @Override
    public boolean contient_Caisse() {
        return true;
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
}
