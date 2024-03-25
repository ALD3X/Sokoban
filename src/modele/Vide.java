package modele;

class Vide extends Element {
    public Vide() {
        this.symbole = '/';
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