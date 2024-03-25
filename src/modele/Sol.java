package modele;

class Sol extends Element {
    public Sol() {
        this.symbole = ' ';
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
