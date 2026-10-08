public class Estalador extends Inimigo {

    public Estalador() {
        super("Estalador", 50, 14, 30, 15);
    }

    @Override
    public void atacar(Combatente alvo) {
        if (Sorte.chance(25)) {
            int dano = getDano() * 2;
            System.out.println("O Estalador AGARRA " + alvo.getNome() + " (" + dano + " de dano)!");
            alvo.receberDano(dano);
        } else {
            System.out.println("O Estalador investe contra " + alvo.getNome()
                    + " (" + getDano() + " de dano).");
            alvo.receberDano(getDano());
        }
    }
}
