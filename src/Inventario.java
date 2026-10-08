public class Inventario {
    private final Item[] itens;
    private int quantidade = 0;

    public Inventario(int capacidade) {
        this.itens = new Item[capacidade];
    }

    public boolean adicionar(Item item) {
        if (quantidade >= itens.length) {
            return false;
        }
        itens[quantidade] = item;
        quantidade++;
        return true;
    }

    public boolean remover(Item item) {
        for (int i = 0; i < quantidade; i++) {
            if (itens[i] == item) {
                removerPosicao(i);
                return true;
            }
        }
        return false;
    }

    /** Remove o item da posição e "puxa" os seguintes para fechar o buraco. */
    private void removerPosicao(int posicao) {
        for (int i = posicao; i < quantidade - 1; i++) {
            itens[i] = itens[i + 1];
        }
        quantidade--;
        itens[quantidade] = null;
    }

    public int contarMateriais() {
        int total = 0;
        for (int i = 0; i < quantidade; i++) {
            if (itens[i] instanceof Material) {
                total++;
            }
        }
        return total;
    }

    public void removerMateriais(int quantidadeParaRemover) {
        int removidos = 0;
        int i = 0;
        while (i < quantidade && removidos < quantidadeParaRemover) {
            if (itens[i] instanceof Material) {
                removerPosicao(i);
                removidos++;
            } else {
                i++;
            }
        }
    }

    public Item getItem(int posicao) {
        return itens[posicao];
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getCapacidade() {
        return itens.length;
    }
}
