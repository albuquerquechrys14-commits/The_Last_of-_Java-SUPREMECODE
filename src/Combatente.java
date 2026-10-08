public interface Combatente {
    void atacar(Combatente alvo);

    void receberDano(int dano);

    boolean estaVivo();

    String getNome();
}
