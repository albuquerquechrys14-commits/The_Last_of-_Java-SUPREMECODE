public abstract class Arma extends Item {
    private final int dano;
    private int municao;
    private final int municaoMaxima;

    protected Arma(String nome, String descricao, int dano, int municaoInicial, int municaoMaxima) {
        super(nome, descricao);
        this.dano = dano;
        this.municaoMaxima = municaoMaxima;
        this.municao = Math.min(municaoInicial, municaoMaxima);
    }

    public abstract boolean usaMunicao();

    public abstract boolean isSilenciosa();

    public int getDano() {
        return dano;
    }

    public int getMunicao() {
        return municao;
    }

    public boolean podeAtacar() {
        return !usaMunicao() || municao > 0;
    }

    public void disparar() {
        if (usaMunicao() && municao > 0) {
            municao--;
        }
    }

    public int recarregar(int quantidade) {
        int antes = municao;
        municao = Math.min(municaoMaxima, municao + quantidade);
        return municao - antes;
    }

    @Override
    public boolean usar(Sobrevivente usuario) {
        usuario.equipar(this);
        return true;
    }

    @Override
    public boolean isConsumivel() {
        return false;
    }

    @Override
    public String toString() {
        String infoMunicao = usaMunicao() ? " [" + municao + "/" + municaoMaxima + "]" : "";
        return getNome() + infoMunicao + " (dano " + dano + (isSilenciosa() ? ", silenciosa" : "") + ")";
    }
}
