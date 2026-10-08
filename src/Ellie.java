public class Ellie extends Sobrevivente {

    public Ellie() {
        super("Ellie", 100, 10, new Faca());
        getInventario().adicionar(new Arco());
        getInventario().adicionar(new KitMedico());
        getInventario().adicionar(new Municao(4));
    }


    @Override
    protected int calcularDano(Arma arma) {
        return arma.isSilenciosa() ? arma.getDano() + 6 : arma.getDano();
    }

    @Override
    public int getChanceFurtividade() {
        return 70;
    }

    @Override
    public void receberDano(int dano) {
        if (dano > 0 && Sorte.chance(15)) {
            System.out.println("Ellie se esquiva do golpe!");
            return;
        }
        super.receberDano(dano);
    }

    @Override
    public String getDescricao() {
        return "Ágil (15% de esquiva) e muito furtiva. Armas silenciosas causam mais dano.";
    }
}
