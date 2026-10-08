public class Sorte {

    private Sorte() {
        // não faz sentido criar objetos desta classe
    }

    /** Retorna true com a probabilidade informada (0 a 100). */
    public static boolean chance(int percentual) {
        return (int) (Math.random() * 100) < percentual;
    }
}
