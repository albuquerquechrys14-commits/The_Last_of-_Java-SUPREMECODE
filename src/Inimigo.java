public abstract class Inimigo extends Personagem {
    private final int dano;
    private final int pontos;
    private final int modificadorFurtivo;

    protected Inimigo(String nome, int vida, int dano, int pontos, int modificadorFurtivo) {
        super(nome, vida);
        this.dano = dano;
        this.pontos = pontos;
        this.modificadorFurtivo = modificadorFurtivo;
    }

    public int getDano() {
        return dano;
    }

    public int getPontos() {
        return pontos;
    }

    public int getModificadorFurtivo() {
        return modificadorFurtivo;
    }
}
