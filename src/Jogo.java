public class Jogo {
    private static final int BONUS_FASE = 50;
    private static final int PONTOS_POR_ITEM = 5;
    private static final int CURA_ACAMPAMENTO = 30;

    private final Entrada entrada;
    private Sobrevivente jogador;
    private int pontuacao;

    public Jogo(Entrada entrada) {
        this.entrada = entrada;
    }

    public void iniciar() {
        System.out.println("==============================================");
        System.out.println("|              THE LAST OF JAVA              |");
        System.out.println("==============================================");
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1) Novo jogo");
            System.out.println("2) Como jogar");
            System.out.println("0) Sair");
            int opcao = entrada.lerOpcao("Opção: ", 0, 2);
            switch (opcao) {
                case 1 -> jogar();
                case 2 -> mostrarRegras();
                default -> rodando = false;
            }
        }
        System.out.println("\nObrigado por jogar. Continue lutando!");
    }

    private void jogar() {
        escolherPersonagem();
        pontuacao = 0;
        Fase[] fases = criarFases();

        for (int i = 0; i < fases.length; i++) {
            Fase fase = fases[i];
            System.out.println("\n>>> FASE " + (i + 1) + "/" + fases.length + ": " + fase.getNome());
            System.out.println(fase.getDescricao());
            entrada.esperarEnter();

            Combate combate = new Combate(jogador, fase, entrada);
            Combate.Resultado resultado = combate.executar();
            pontuacao += combate.getPontosGanhos();

            if (resultado == Combate.Resultado.DERROTA) {
                telaDerrota();
                return;
            }
            if (resultado == Combate.Resultado.VITORIA) {
                System.out.println("\nÁrea limpa!");
                pontuacao += BONUS_FASE;
                coletarRecompensas(fase);
            } else {
                System.out.println("\nVocê seguiu em frente sem saquear a área.");
            }
            if (i < fases.length - 1) {
                acampamento();
            }
        }
        telaVitoria();
    }

    private void escolherPersonagem() {
        System.out.println("\nEscolha seu sobrevivente:");
        Sobrevivente joel = new Joel();
        Sobrevivente ellie = new Ellie();
        System.out.println("1) Joel  - " + joel.getDescricao());
        System.out.println("2) Ellie - " + ellie.getDescricao());
        int opcao = entrada.lerOpcao("Personagem: ", 1, 2);
        jogador = (opcao == 1) ? joel : ellie;
        System.out.println("\nVocê controla " + jogador.getNome() + ". Boa sorte.");
    }

    private Fase[] criarFases() {
        Fase f1 = new Fase("Quarentena de Boston",
                "A zona de quarentena caiu. Infectados rondam as ruas escuras.");
        f1.adicionarInimigo(new Corredor());
        f1.adicionarInimigo(new Corredor());
        f1.adicionarRecompensa(new KitMedico());
        f1.adicionarRecompensa(new Municao(6));
        f1.adicionarRecompensa(new Material());

        Fase f2 = new Fase("Pittsburgh",
                "Uma emboscada de saqueadores em uma cidade coberta de neve.");
        f2.adicionarInimigo(new Saqueador());
        f2.adicionarInimigo(new Saqueador());
        f2.adicionarInimigo(new Corredor());
        f2.adicionarRecompensa(new KitMedico());
        f2.adicionarRecompensa(new Municao(6));
        f2.adicionarRecompensa(new Material());

        Fase f3 = new Fase("Hospital de Salt Lake City",
                "Corredores escuros, esporos e estaladores. Mantenha silêncio.");
        f3.adicionarInimigo(new Estalador());
        f3.adicionarInimigo(new Estalador());
        f3.adicionarInimigo(new Saqueador());
        f3.adicionarRecompensa(new KitMedico());
        f3.adicionarRecompensa(new Municao(8));
        f3.adicionarRecompensa(new Material());

        Fase f4 = new Fase("Seattle",
                "O destino final. Um Brutamontes e seus aliados bloqueiam o caminho.");
        f4.adicionarInimigo(new Brutamontes());
        f4.adicionarInimigo(new Saqueador());
        f4.adicionarInimigo(new Saqueador());

        return new Fase[] { f1, f2, f3, f4 };
    }

    private void coletarRecompensas(Fase fase) {
        for (int i = 0; i < fase.getTotalRecompensas(); i++) {
            Item item = fase.getRecompensa(i);
            if (jogador.getInventario().adicionar(item)) {
                System.out.println("Coletou: " + item.getNome());
                pontuacao += PONTOS_POR_ITEM;
            } else {
                System.out.println("Mochila cheia! Deixou para trás: " + item.getNome());
            }
        }
    }

    private void acampamento() {
        int curado = jogador.curar(CURA_ACAMPAMENTO);
        System.out.println("\nVocê descansa em um abrigo e recupera " + curado + " de vida.");
        System.out.println(jogador);
        System.out.println("Pontuação atual: " + pontuacao);
        entrada.esperarEnter();
    }

    private void telaVitoria() {
        pontuacao += jogador.getVida();
        System.out.println("\n*************** VITÓRIA ***************");
        System.out.println(jogador.getNome() + " sobreviveu a todas as fases!");
        System.out.println("Pontuação final: " + pontuacao);
        System.out.println("Classificação: " + classificar());
    }

    private void telaDerrota() {
        System.out.println("\n*************** DERROTA ***************");
        System.out.println(jogador.getNome() + " não resistiu...");
        System.out.println("Pontuação final: " + pontuacao);
    }

    private String classificar() {
        if (pontuacao >= 550) {
            return "Lenda dos Vagalumes";
        } else if (pontuacao >= 400) {
            return "Sobrevivente Veterano";
        }
        return "Sobrevivente";
    }

    private void mostrarRegras() {
        System.out.println("\n--- COMO JOGAR ---");
        System.out.println("Objetivo: atravessar as 4 fases eliminando os inimigos e sobreviver.");
        System.out.println("Cada fase é um combate por turnos. Depois da sua ação, todos os inimigos atacam.");
        System.out.println("Ataque furtivo: só com arma silenciosa e enquanto ninguém te notou.");
        System.out.println("Munição e itens são limitados: use a mochila com estratégia.");
        System.out.println("Vitória: terminar a fase 4. Derrota: sua vida chegar a zero.");
        System.out.println("Pontos: inimigos, itens coletados, fases concluídas e vida restante.");
    }
}
