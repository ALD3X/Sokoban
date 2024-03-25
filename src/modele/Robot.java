package modele;

class Robot extends Element {
    public Robot() {
        this.symbole = '@';
    }
    
    @Override
    public boolean contient_Robot() {
        return true;
    }

    @Override
    public boolean contient_Caisse() {
        return false;
    }
}
