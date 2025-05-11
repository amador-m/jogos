package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sudoku.modelo.Espaco;
import sudoku.modelo.Jogadas;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static Jogadas tabuleiro;

    public static void main(String[] args) {
        int opcao = -1;

        while (true) {
            System.out.println("Menu de opções:");
            System.out.println("\t1 - Iniciar um novo jogo");
            System.out.println("\t2 - Inserir um número");
            System.out.println("\t3 - Remover um número");
            System.out.println("\t4 - Mostrar tabuleiro");
            System.out.println("\t5 - Verificar status");
            System.out.println("\t6 - Limpar jogo");
            System.out.println("\t7 - Finalizar jogo");
            System.out.println("\t8 - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    inserirNumero();
                    break;
                case 3:
                    removerNumero();
                    break;
                case 4:
                    mostrarTabuleiro();
                    break;
                case 5:
                    verificarStatus();
                    break;
                case 6:
                    limparJogo();
                    break;
                case 7:
                    finalizarJogo();
                    break;
                case 8:
                    System.out.println("Saindo");
                    return; 
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void iniciarJogo() {
        if (tabuleiro != null) {
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Espaco>> espacos = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            espacos.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                espacos.get(i).add(new Espaco((int) (Math.random() * 9) + 1, Math.random() > 0.5));
            }
        }
        tabuleiro = new Jogadas(espacos);
        System.out.println("O jogo começou!");
    }

    private static void inserirNumero() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.print("Informe a coluna: ");
        int c = input.nextInt();
        System.out.print("Informe a linha: ");
        int l = input.nextInt();
        System.out.print("Informe o número: ");
        int valor = input.nextInt();

        if (!tabuleiro.alterarValor(l, c, valor)) {
            System.out.println("A posição [" + l + "," + c + "] tem um valor fixo");
        }
    }

    private static void removerNumero() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.print("Informe a coluna:");
        int c = input.nextInt();
        System.out.print("Informe a linha:");
        int l = input.nextInt();

        if (!tabuleiro.limparValor(l, c)) {
            System.out.println("A posição [" + l + "," + c + "] tem um valor fixo");
        }
    }

    private static void mostrarTabuleiro() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }
        tabuleiro.mostrar();
    }

    private static void verificarStatus() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Status: " + tabuleiro.getStatus());
        if (tabuleiro.temErros()) {
            System.out.println("O jogo contém erros!");
        } else {
            System.out.println("O jogo está correto");
        }
    }

    private static void limparJogo() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }
        System.out.print("Tem certeza que deseja limpar o jogo? (sim/não) ");
        String confirmacao = input.next();
        if (confirmacao.equalsIgnoreCase("sim")) {
            tabuleiro.resetar();
            System.out.println("Jogo limpo");
        }
    }

    private static void finalizarJogo() {
        if (tabuleiro == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (tabuleiro.jogoFinalizado()) {
            System.out.println("Parabéns, você concluiu o jogo!");
            tabuleiro = null;
        } else {
            System.out.println("Ainda há erros ou espaços vazios");
        }
    }
}