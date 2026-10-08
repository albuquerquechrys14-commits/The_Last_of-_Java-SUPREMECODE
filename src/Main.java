public class Main {
    public static void main(String[] args) {
        try {
            new Jogo(new Entrada()).iniciar();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Até a próxima!");
        }
    }
}
