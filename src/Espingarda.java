public class Espingarda extends Arma {
    public Espingarda() {
        super("Espingarda", "Dano devastador a curta distância", 35, 4, 10);
    }

    @Override
    public boolean usaMunicao() {
        return true;
    }

    @Override
    public boolean isSilenciosa() {
        return false;
    }
}
