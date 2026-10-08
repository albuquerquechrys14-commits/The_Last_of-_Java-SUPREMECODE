public class Joel extends Sobrevivente {

    public Joel() {
        super("Joel", 120, 10, new Pistola());
        getInventario().adicionar(new Faca());
        getInventario().adicionar(new Espingarda());
        getInventario().adicionar(new KitMedico());
        getInventario().adicionar(new Municao(6));
    }


    @Override
    protected int calcularDano(Arma arma) {
        return arma.usaMunicao() ? arma.getDano() : arma.getDano() + 8;
    }

    @Override
    public int getChanceFurtividade() {
        return 40;
    }

    @Override
    public void receberDano(int dano) {
        super.receberDano((int) Math.ceil(dano * 0.85));
    }

    @Override
    public String getDescricao() {
        return "Resistente (-15% de dano recebido) e forte no corpo a corpo. Pouco furtivo.";
    }
}
