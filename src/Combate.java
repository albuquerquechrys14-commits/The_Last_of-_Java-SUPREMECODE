public class Combate {
    public enum Resultado { VITORIA, DERROTA, FUGA }

    private static final int BONUS_FURTIVO = 10;
    private static final int CHANCE_FUGA = 40;

    private final Sobrevivente jogador;
    private final Fase fase;
    private final Entrada entrada;
    private int pontosGanhos = 0;
    private boolean alertado = false;

    public Combate(Sobrevivente jogador, Fase fase, Entrada entrada) {
        this.jogador = jogador;
        this.fase = fase;
        this.entrada = entrada;
    }

    public Resultado executar() {
        while (jogador.estaVivo() && fase.temInimigosVivos()) {
            mostrarStatus();
            System.out.println("1) Atacar");
            System.out.println("2) Ataque furtivo");
            System.out.println("3) Usar item / trocar de arma");
            System.out.println("4) Criar kit médico (2 materiais)");
            System.out.println("5) Fugir");
            int opcao = entrada.lerOpcao("Escolha sua ação: ", 1, 5);
            System.out.println();

            boolean gastouTurno;
            if (opcao == 5) {
                if (tentarFuga()) {
                    return Resultado.FUGA;
                }
                gastouTurno = true;
            } else {
                gastouTurno = switch (opcao) {
                    case 1 -> atacar();
                    case 2 -> atacarFurtivo();
                    case 3 -> usarItem();
                    default -> criarKit();
                };
            }

            if (gastouTurno && fase.temInimigosVivos()) {
                turnoInimigos();
            }
        }
        return jogador.estaVivo() ? Resultado.VITORIA : Resultado.DERROTA;
    }

    private boolean atacar() {
        Inimigo alvo = escolherAlvo();
        alertado = true;
        jogador.atacar(alvo);
        verificarMorte(alvo);
        return true;
    }

    private boolean atacarFurtivo() {
        Arma arma = jogador.getArmaEquipada();
        if (alertado) {
            System.out.println("Os inimigos já notaram você! Não dá mais para ser furtivo.");
            return false;
        }
        if (!arma.isSilenciosa() || !arma.podeAtacar()) {
            System.out.println("Você precisa de uma arma silenciosa (com munição) para isso.");
            return false;
        }
        Inimigo alvo = escolherAlvo();
        int chance = jogador.getChanceFurtividade() + alvo.getModificadorFurtivo();
        chance = Math.max(0, Math.min(95, chance));
        if (chance == 0) {
            System.out.println("É impossível surpreender um " + alvo.getNome() + ".");
            return false;
        }
        System.out.println(jogador.getNome() + " se aproxima de " + alvo.getNome()
                + " em silêncio... (chance: " + chance + "%)");
        arma.disparar();
        if (Sorte.chance(chance)) {
            alvo.receberDano(alvo.getVida());
            System.out.println("Eliminação silenciosa! +" + BONUS_FURTIVO + " pontos de bônus.");
            pontosGanhos += BONUS_FURTIVO;
            verificarMorte(alvo);
        } else {
            alertado = true;
            System.out.println("Você foi notado! Os inimigos estão em alerta.");
        }
        return true;
    }

    private boolean usarItem() {
        Inventario mochila = jogador.getInventario();
        if (mochila.getQuantidade() == 0) {
            System.out.println("Sua mochila está vazia.");
            return false;
        }
        System.out.println("Mochila (" + mochila.getQuantidade() + "/" + mochila.getCapacidade() + "):");
        for (int i = 0; i < mochila.getQuantidade(); i++) {
            System.out.println((i + 1) + ") " + mochila.getItem(i));
        }
        System.out.println("0) Cancelar");
        int escolha = entrada.lerOpcao("Item: ", 0, mochila.getQuantidade());
        if (escolha == 0) {
            return false;
        }
        return jogador.usarItem(mochila.getItem(escolha - 1));
    }

    private boolean criarKit() {
        return jogador.criarKitMedico();
    }

    private boolean tentarFuga() {
        if (Sorte.chance(CHANCE_FUGA)) {
            System.out.println(jogador.getNome() + " escapa pela saída, sem tempo de pegar nada!");
            return true;
        }
        System.out.println("Não deu para fugir! Os inimigos cercam você.");
        return false;
    }

    private void turnoInimigos() {
        System.out.println("\n--- Turno dos inimigos ---");
        Inimigo[] vivos = fase.getInimigosVivos();
        for (int i = 0; i < vivos.length; i++) {
            vivos[i].atacar(jogador);
            if (!jogador.estaVivo()) {
                break;
            }
        }
    }

    private Inimigo escolherAlvo() {
        Inimigo[] vivos = fase.getInimigosVivos();
        if (vivos.length == 1) {
            return vivos[0];
        }
        System.out.println("Escolha o alvo:");
        for (int i = 0; i < vivos.length; i++) {
            System.out.println((i + 1) + ") " + vivos[i]);
        }
        int escolha = entrada.lerOpcao("Alvo: ", 1, vivos.length);
        return vivos[escolha - 1];
    }

    private void verificarMorte(Inimigo inimigo) {
        if (!inimigo.estaVivo()) {
            pontosGanhos += inimigo.getPontos();
            System.out.println(inimigo.getNome() + " eliminado! +" + inimigo.getPontos() + " pontos.");
        }
    }

    private void mostrarStatus() {
        System.out.println("\n==================================================");
        System.out.println(jogador);
        System.out.println("Arma: " + jogador.getArmaEquipada());
        System.out.println(alertado ? "Situação: ALERTA" : "Situação: furtivo");
        System.out.println("Inimigos:");
        Inimigo[] vivos = fase.getInimigosVivos();
        for (int i = 0; i < vivos.length; i++) {
            System.out.println("  - " + vivos[i]);
        }
        System.out.println("==================================================");
    }

    public int getPontosGanhos() {
        return pontosGanhos;
    }
}
