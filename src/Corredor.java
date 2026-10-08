public class Corredor extends Inimigo {

    public Corredor() {
        super("Corredor", 30, 8, 10, -10);
    }

    @Override
    public void atacar(Combatente alvo) {
        int golpe = getDano() / 2;
        System.out.println("O Corredor avança e ataca duas vezes (" + golpe + " + " + golpe + ")!");
        alvo.receberDano(golpe);
        alvo.receberDano(golpe);
    }
}
