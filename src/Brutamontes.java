public class Brutamontes extends Inimigo {
    private int turno = 0;

    public Brutamontes() {
        super("Brutamontes", 130, 22, 100, -100);
    }

    @Override
    public void atacar(Combatente alvo) {
        turno++;
        if (turno % 3 == 0) {
            int dano = (int) (getDano() * 1.5);
            System.out.println("O Brutamontes arranca um bloco de concreto e arremessa (" + dano + " de dano)!");
            alvo.receberDano(dano);
        } else {
            System.out.println("O Brutamontes esmaga " + alvo.getNome() + " (" + getDano() + " de dano).");
            alvo.receberDano(getDano());
        }
    }
}
