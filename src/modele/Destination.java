package modele;

class Destination extends Element {
    public Destination() {
        this.symbole = '.';
    }
    
    @Override
    public boolean contient_Robot() {
        return false;
    }

    @Override
    public boolean contient_Caisse() {
        return false;
    }
}
