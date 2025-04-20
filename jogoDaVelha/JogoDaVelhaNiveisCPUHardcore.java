package jogos;

import java.util.Random;
import java.util.Scanner;

public class JogoDaVelhaNiveisCPUHardcore {
    private static int jogador; // 1 para Humano (X), 2 para CPU (O)
    private static int[][] casa = new int[3][3];
    private static int vencedor; // 0: nenhum, 1: Humano, 2: CPU, 3: Empate (vamos adicionar)
    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();

    // Constantes para jogadores para clareza
    static final int PLAYER_X = 1;
    static final int PLAYER_O = 2;

    public static void posicoes(int x, int y) {
        if (casa[x][y] == PLAYER_X) {
            System.out.print("X");
        } else if (casa[x][y] == PLAYER_O) {
            System.out.print("O");
        } else {
            System.out.print(" ");
        }
    }

    public static void tabuleiro() {
        System.out.print("\n   1   2    3\n");
        System.out.print("1 ");
        posicoes(0, 0);
        System.out.print("  | ");
        posicoes(0, 1);
        System.out.print("  | ");
        posicoes(0, 2);
        System.out.print("\n  ------------\n2 ");
        posicoes(1, 0);
        System.out.print("  | ");
        posicoes(1, 1);
        System.out.print("  | ");
        posicoes(1, 2);
        System.out.print("\n  ------------\n3 ");
        posicoes(2, 0);
        System.out.print("  | ");
        posicoes(2, 1);
        System.out.print("  | ");
        posicoes(2, 2);
         System.out.println(); // Nova linha no final
    }

    public static void jogadorHumano() {
        jogador = PLAYER_X;
        System.out.println("\n\nSua vez (Jogador 1: X)");
        int l, c; // Declarar localmente
        while (true) { // Loop infinito até jogada válida
            l = 0;
            c = 0;
            while (l < 1 || l > 3) {
                System.out.print("\t- Escolha a Linha (1,2,3): ");
                 // Tratamento de erro básico se não for int
                if (input.hasNextInt()) {
                    l = input.nextInt();
                    if (l < 1 || l > 3) System.out.println("Linha inválida.");
                } else {
                    System.out.println("Entrada inválida. Digite um número.");
                    input.next(); // Consome a entrada inválida
                }
            }
            while (c < 1 || c > 3) {
                System.out.print("\t- Escolha a Coluna (1,2,3): ");
                if (input.hasNextInt()) {
                    c = input.nextInt();
                    if (c < 1 || c > 3) System.out.println("Coluna inválida.");
                } else {
                    System.out.println("Entrada inválida. Digite um número.");
                    input.next(); // Consome a entrada inválida
                }
            }
            l -= 1; // Ajusta para índice 0-based
            c -= 1; // Ajusta para índice 0-based

            if (l >= 0 && l < 3 && c >= 0 && c < 3 && casa[l][c] == 0) {
                casa[l][c] = jogador;
                break; // Sai do loop while(true) com jogada válida
            } else if (l >= 0 && l < 3 && c >= 0 && c < 3) { // Verifica se o índice é válido antes de checar casa[l][c]
                 System.out.println("Posição ocupada! Tente novamente");
            }
            // Se a linha/coluna foi inválida, o loop continua
        }
    }

    // --- Funções Minimax ---

    // Classe interna para representar uma jogada (coordenadas)
    static class Move {
        int row, col;
    };

    // Verifica se ainda há jogadas possíveis
    static Boolean isMovesLeft(int board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == 0)
                    return true;
        return false;
    }

    // Avalia o tabuleiro para o Minimax
    static int evaluate(int b[][]) {
        // Checando Linhas por vitória X ou O
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == PLAYER_O) return +10; // CPU Ganhou
                else if (b[row][0] == PLAYER_X) return -10; // Humano Ganhou
            }
        }
        // Checando Colunas por vitória X ou O
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == PLAYER_O) return +10;
                else if (b[0][col] == PLAYER_X) return -10;
            }
        }
        // Checando Diagonais por vitória X ou O
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == PLAYER_O) return +10;
            else if (b[0][0] == PLAYER_X) return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == PLAYER_O) return +10;
            else if (b[0][2] == PLAYER_X) return -10;
        }
        // Se ninguém ganhou, retorna 0 (Empate ou jogo continua)
        return 0;
    }

    // Função Minimax recursiva
    static int minimax(int board[][], int depth, Boolean isMax) {
        int score = evaluate(board);

        // Se Maximizer (CPU) venceu
        if (score == 10) return score - depth; // Penaliza vitórias mais demoradas ligeiramente

        // Se Minimizer (Humano) venceu
        if (score == -10) return score + depth; // Penaliza derrotas mais rápidas ligeiramente

        // Se for empate (sem mais jogadas)
        if (!isMovesLeft(board)) return 0;

        // Se for a vez do Maximizer (CPU)
        if (isMax) {
            int best = -1000;
            // Percorre todas as células
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Checa se célula está vazia
                    if (board[i][j] == 0) {
                        // Faz a jogada (temporária)
                        board[i][j] = PLAYER_O;
                        // Chama minimax recursivamente e escolhe o valor máximo
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        // Desfaz a jogada
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
        // Se for a vez do Minimizer (Humano)
        else {
            int best = 1000;
            // Percorre todas as células
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                     // Checa se célula está vazia
                    if (board[i][j] == 0) {
                        // Faz a jogada (temporária)
                        board[i][j] = PLAYER_X;
                        // Chama minimax recursivamente e escolhe o valor mínimo
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        // Desfaz a jogada
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

    // Encontra a melhor jogada para a CPU
    static Move findBestMove(int board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Percorre todas as células, avalia minimax para todas as células vazias.
        // Retorna a célula com o valor ótimo.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 // Checa se célula está vazia
                if (board[i][j] == 0) {
                    // Faz a jogada
                    board[i][j] = PLAYER_O;
                    // Calcula o valor minimax para esta jogada
                    // A CPU (Max) fez uma jogada, então o próximo é Min (isMax = false)
                    int moveVal = minimax(board, 0, false);
                    // Desfaz a jogada
                    board[i][j] = 0;

                    // Se o valor da jogada atual é melhor que o melhor valor, atualiza bestVal
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        //System.out.printf("O valor da melhor jogada é: %d\n\n", bestVal); // Debug
        return bestMove;
    }

    // --- Fim Funções Minimax ---


    // Função jogarCPU modificada
    public static void jogarCPU(int dificuldade) {
        jogador = PLAYER_O; // CPU é sempre O
        System.out.println("\n\nVez da CPU (Jogador 2: O)");
        boolean jogou = false; // Não estritamente necessário com minimax, mas mantém padrão

        if (dificuldade == 3) { // Nível Infalível (Minimax)
            Move bestMove = findBestMove(casa);
            if (bestMove.row != -1) { // Verifica se uma jogada foi encontrada
                casa[bestMove.row][bestMove.col] = jogador;
                System.out.println("CPU (Minimax) jogou na linha " + (bestMove.row + 1) + " e coluna " + (bestMove.col + 1));
                jogou = true;
            } else {
                 System.out.println("CPU (Minimax) não encontrou jogada? (Isso não deveria acontecer em um tabuleiro não cheio)");
                 // Fallback para aleatório se algo der muito errado (não deve ocorrer)
            }
        }

        // Níveis 1 e 2 (usando a lógica antiga, se desejar, ou simplificar)
        // Por exemplo, nível 2 pode ser apenas o bloqueio + aleatório
        if (!jogou && dificuldade == 2) {
             int[] jogadaCoords = encontrarJogadaCritica(PLAYER_X); // Tenta bloquear
             if (jogadaCoords != null) {
                 casa[jogadaCoords[0]][jogadaCoords[1]] = jogador;
                 System.out.println("CPU (Médio - Bloquear) jogou na linha " + (jogadaCoords[0] + 1) + " e coluna " + (jogadaCoords[1] + 1));
                 jogou = true;
             }
        }

         // Nível 1 (Fácil - Aleatório) ou Fallback dos outros níveis
         if (!jogou) {
             // Verifica se ainda há casas vazias
             if (isMovesLeft(casa)) {
                 int l_rand, c_rand;
                 do {
                     l_rand = random.nextInt(3);
                     c_rand = random.nextInt(3);
                 } while (casa[l_rand][c_rand] != 0);
                 casa[l_rand][c_rand] = jogador;
                 System.out.println("CPU (Fácil/Aleatório) jogou na linha " + (l_rand + 1) + " e coluna " + (c_rand + 1));
             } else {
                  System.out.println("CPU não jogou (sem espaços ou erro).");
             }
         }
    }

    // Função check() para verificar o vencedor *após* a jogada
     public static void check() {
        int score = evaluate(casa); // Usa a função de avaliação do minimax

        if (score == 10) {
            vencedor = PLAYER_O; // CPU Venceu
        } else if (score == -10) {
            vencedor = PLAYER_X; // Humano Venceu
        } else if (!isMovesLeft(casa)) {
             vencedor = 3; // Empate
        } else {
            vencedor = 0; // Jogo continua
        }
    }


    public static void limparTabuleiro() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                casa[x][y] = 0;
            }
        }
        vencedor = 0; // Resetar vencedor
    }

    public static boolean desejaReiniciar() {
        System.out.print("\nDeseja jogar novamente? (S/N): ");
        String resposta = input.next();
        return resposta.equalsIgnoreCase("S");
    }

    public static void main(String[] args) {
        boolean jogarNovamente = true;
        while (jogarNovamente) {
            System.out.println("\nNíveis de dificuldade: 1 - Fácil | 2 - Médio | 3 - Impossível (Minimax)");
            System.out.print("Qual nível você deseja? ");
            int dificuldade = 1; // Default
             if (input.hasNextInt()) {
                 dificuldade = input.nextInt();
                 if (dificuldade < 1 || dificuldade > 3) {
                     System.out.println("Nível inválido, usando Fácil (1).");
                     dificuldade = 1;
                 }
             } else {
                 System.out.println("Entrada inválida, usando Fácil (1).");
                 input.next(); // Consome entrada inválida
             }


            limparTabuleiro();
            int numeroDeJogadas = 0;

            // Determina quem começa (ex: Humano sempre começa)
            // Poderia alternar ou sortear
            int jogadorAtual = PLAYER_X;

            while (vencedor == 0 && numeroDeJogadas < 9) {
                tabuleiro();
                if (jogadorAtual == PLAYER_X) {
                    jogadorHumano();
                    jogadorAtual = PLAYER_O;
                } else {
                    jogarCPU(dificuldade);
                    jogadorAtual = PLAYER_X;
                }
                numeroDeJogadas++;
                check(); // Verifica o estado do jogo após cada jogada
            }

            // Exibe o tabuleiro final e o resultado
            tabuleiro();
            if (vencedor == PLAYER_X) {
                System.out.println("\nVocê venceu!");
            } else if (vencedor == PLAYER_O) {
                System.out.println("\nA CPU venceu!");
            } else { // Inclui vencedor == 3 (empate explícito) ou se saiu do loop por 9 jogadas sem vencedor
                System.out.println("\nO jogo empatou.");
            }

            jogarNovamente = desejaReiniciar();
        }
        System.out.println("Obrigado por jogar!");
        input.close(); // Fechar o scanner ao final
    }

     // Função encontrarJogadaCritica (usada pelo nível 2 - pode manter ou remover se nível 2 for só aleatório)
     public static int[] encontrarJogadaCritica(int jogadorAlvo) {
         // linhas
         for(int i = 0; i < 3; i++) {
             if (casa[i][0] == jogadorAlvo && casa[i][1] == jogadorAlvo && casa[i][2] == 0) return new int[]{i, 2};
             if (casa[i][0] == jogadorAlvo && casa[i][2] == jogadorAlvo && casa[i][1] == 0) return new int[]{i, 1};
             if (casa[i][1] == jogadorAlvo && casa[i][2] == jogadorAlvo && casa[i][0] == 0) return new int[]{i, 0};
         }
         // colunas
         for(int i = 0; i < 3; i++) {
             if (casa[0][i] == jogadorAlvo && casa[1][i] == jogadorAlvo && casa[2][i] == 0) return new int[]{2, i};
             if (casa[0][i] == jogadorAlvo && casa[2][i] == jogadorAlvo && casa[1][i] == 0) return new int[]{1, i};
             if (casa[1][i] == jogadorAlvo && casa[2][i] == jogadorAlvo && casa[0][i] == 0) return new int[]{0, i};
         }
         // diagonais
         if (casa[0][0] == jogadorAlvo && casa[1][1] == jogadorAlvo && casa[2][2] == 0) return new int[]{2, 2};
         if (casa[0][0] == jogadorAlvo && casa[2][2] == jogadorAlvo && casa[1][1] == 0) return new int[]{1, 1};
         if (casa[1][1] == jogadorAlvo && casa[2][2] == jogadorAlvo && casa[0][0] == 0) return new int[]{0, 0};
         if (casa[0][2] == jogadorAlvo && casa[1][1] == jogadorAlvo && casa[2][0] == 0) return new int[]{2, 0};
         if (casa[0][2] == jogadorAlvo && casa[2][0] == jogadorAlvo && casa[1][1] == 0) return new int[]{1, 1};
         if (casa[1][1] == jogadorAlvo && casa[2][0] == jogadorAlvo && casa[0][2] == 0) return new int[]{0, 2};

         return null; // Nenhuma jogada crítica encontrada
     }
}
// Aprimorado por IA  para tornar o jogo mais desafiador :)