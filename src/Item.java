public abstract class Item implements Usavel {
    private final String nome;
    private final String descricao;

    protected Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConsumivel() {
        return true;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
