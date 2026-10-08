public class Faca extends Arma {
    public Faca() {
        super("Faca", "Arma branca improvisada", 8, 0, 0);
    }

    @Override
    public boolean usaMunicao() {
        return false;
    }

    @Override
    public boolean isSilenciosa() {
        return true;
    }
}
