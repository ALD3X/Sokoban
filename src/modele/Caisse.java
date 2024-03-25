package modele;

class Caisse extends Element {
    public Caisse() {
        this.symbole = '$';
    }
    
    @Override
    public boolean contient_Robot() {
        return false;
    }

    @Override
    public boolean contient_Caisse() {
        return true;
    }
}
