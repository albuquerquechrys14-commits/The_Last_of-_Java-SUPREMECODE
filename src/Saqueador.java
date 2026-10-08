public class Saqueador extends Inimigo {

    public Saqueador() {
        super("Saqueador", 40, 10, 20, 0);
    }

    @Override
    public void atacar(Combatente alvo) {
        if (Sorte.chance(25)) {
            System.out.println("O Saqueador atira e erra!");
        } else {
            System.out.println("O Saqueador atira em " + alvo.getNome()
                    + " (" + getDano() + " de dano).");
            alvo.receberDano(getDano());
        }
    }
}
