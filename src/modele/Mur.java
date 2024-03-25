package modele;

class Mur extends Element {
    public Mur() {
        this.symbole = '#';
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
