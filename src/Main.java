public class Main {
    public static void main(String[] args) {
        try {
            new Jogo(new Entrada()).iniciar();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Até a próxima!");
        }
    }
}
//made by CHRYSTIAN ALBQ. SENAI DEST 1 = FIAMA BRENDA
//10-08-2026