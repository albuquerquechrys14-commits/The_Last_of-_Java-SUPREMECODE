public abstract class Sobrevivente extends Personagem {
    private static final int DANO_SOCO = 3;

    private final Inventario inventario;
    private Arma armaEquipada;

    protected Sobrevivente(String nome, int vidaMaxima, int capacidadeMochila, Arma armaInicial) {
        super(nome, vidaMaxima);
        if (armaInicial == null) {
            throw new IllegalArgumentException("O sobrevivente precisa de uma arma inicial.");
        }
        this.inventario = new Inventario(capacidadeMochila);
        this.armaEquipada = armaInicial;
    }

    /** Cada sobrevivente tem um estilo: o dano final depende da arma e de quem a usa. */
    protected abstract int calcularDano(Arma arma);

    /** Chance base (0 a 100) de eliminar um inimigo em silêncio. */
    public abstract int getChanceFurtividade();

    public abstract String getDescricao();

    @Override
    public void atacar(Combatente alvo) {
        int dano;
        if (armaEquipada.podeAtacar()) {
            dano = calcularDano(armaEquipada);
            armaEquipada.disparar();
            System.out.println(getNome() + " ataca " + alvo.getNome() + " com "
                    + armaEquipada.getNome() + " (" + dano + " de dano).");
        } else {
            dano = DANO_SOCO;
            System.out.println(armaEquipada.getNome() + " está sem munição! "
                    + getNome() + " usa os punhos (" + dano + " de dano).");
        }
        alvo.receberDano(dano);
    }

    public void equipar(Arma nova) {
        inventario.remover(nova);
        Arma antiga = armaEquipada;
        armaEquipada = nova;
        inventario.adicionar(antiga);
        System.out.println(getNome() + " equipou " + nova.getNome() + ".");
    }

    /**
     * Usa um item do inventário. O próprio item decide o que acontece
     * (polimorfismo); se teve efeito e é consumível, ele sai da mochila.
     */
    public boolean usarItem(Item item) {
        boolean teveEfeito = item.usar(this);
        if (teveEfeito && item.isConsumivel()) {
            inventario.remover(item);
        }
        return teveEfeito;
    }

    /** Combina 2 materiais para criar 1 kit médico. */
    public boolean criarKitMedico() {
        if (inventario.contarMateriais() < 2) {
            System.out.println("Você precisa de 2 materiais para criar um kit médico.");
            return false;
        }
        inventario.removerMateriais(2);
        inventario.adicionar(new KitMedico());
        System.out.println(getNome() + " criou um Kit Médico com os materiais.");
        return true;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }
}
