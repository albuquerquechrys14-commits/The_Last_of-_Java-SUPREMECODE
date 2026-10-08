public class Entrada {
    private final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public int lerOpcao(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linha);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem de erro abaixo
            }
            System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ".");
        }
    }

    public void esperarEnter() {
        System.out.print("(ENTER para continuar) ");
        scanner.nextLine();
    }
}
