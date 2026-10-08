public abstract class Personagem implements Combatente {
    private final String nome;
    private final int vidaMaxima;
    private int vida;

    protected Personagem(String nome, int vidaMaxima) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("A vida máxima deve ser positiva.");
        }
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    @Override
    public void receberDano(int dano) {
        if (dano > 0) {
            vida = Math.max(0, vida - dano);
        }
    }

    public int curar(int quantidade) {
        if (quantidade <= 0) {
            return 0;
        }
        int antes = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        return vida - antes;
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    public String getBarraVida() {
        int segmentos = 10;
        int cheios = (int) Math.ceil((double) vida * segmentos / vidaMaxima);
        String barra = "[";
        for (int i = 0; i < segmentos; i++) {
            barra += (i < cheios) ? "#" : "-";
        }
        return barra + "] " + vida + "/" + vidaMaxima;
    }

    @Override
    public String toString() {
        return nome + " " + getBarraVida();
    }
}
