public class Arco extends Arma {
    public Arco() {
        super("Arco", "Silencioso e letal", 25, 5, 12);
    }

    @Override
    public boolean usaMunicao() {
        return true;
    }

    @Override
    public boolean isSilenciosa() {
        return true;
    }
}
