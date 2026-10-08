public class Municao extends Item {
    private final int quantidade;

    public Municao(int quantidade) {
        super("Munição (" + quantidade + ")", "Recarrega a arma equipada");
        this.quantidade = quantidade;
    }

    @Override
    public boolean usar(Sobrevivente usuario) {
        Arma arma = usuario.getArmaEquipada();
        if (!arma.usaMunicao()) {
            System.out.println(arma.getNome() + " não usa munição. Equipe outra arma primeiro.");
            return false;
        }
        int adicionada = arma.recarregar(quantidade);
        if (adicionada == 0) {
            System.out.println(arma.getNome() + " já está com a munição cheia.");
            return false;
        }
        System.out.println(usuario.getNome() + " recarregou " + arma.getNome() + " (+" + adicionada + ").");
        return true;
    }
}
