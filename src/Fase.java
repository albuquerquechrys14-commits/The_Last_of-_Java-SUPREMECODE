public class Fase {
    private static final int MAXIMO = 6;

    private final String nome;
    private final String descricao;
    private final Inimigo[] inimigos = new Inimigo[MAXIMO];
    private int totalInimigos = 0;
    private final Item[] recompensas = new Item[MAXIMO];
    private int totalRecompensas = 0;

    public Fase(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionarInimigo(Inimigo inimigo) {
        if (totalInimigos < MAXIMO) {
            inimigos[totalInimigos++] = inimigo;
        }
    }

    public void adicionarRecompensa(Item item) {
        if (totalRecompensas < MAXIMO) {
            recompensas[totalRecompensas++] = item;
        }
    }

    public Inimigo[] getInimigosVivos() {
        int vivos = 0;
        for (int i = 0; i < totalInimigos; i++) {
            if (inimigos[i].estaVivo()) {
                vivos++;
            }
        }
        Inimigo[] resultado = new Inimigo[vivos];
        int posicao = 0;
        for (int i = 0; i < totalInimigos; i++) {
            if (inimigos[i].estaVivo()) {
                resultado[posicao++] = inimigos[i];
            }
        }
        return resultado;
    }

    public boolean temInimigosVivos() {
        return getInimigosVivos().length > 0;
    }

    public int getTotalRecompensas() {
        return totalRecompensas;
    }

    public Item getRecompensa(int posicao) {
        return recompensas[posicao];
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
