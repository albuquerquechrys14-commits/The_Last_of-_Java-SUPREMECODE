public class Pistola extends Arma {
    public Pistola() {
        super("Pistola", "Tiro rápido e preciso", 20, 8, 24);
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
