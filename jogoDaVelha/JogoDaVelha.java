package jogos;

import java.util.Scanner;

public class JogoDaVelha {
    private static int jogador;
    private static int [][] casa = new int[3][3];
    private static int l, c, vencedor;
    private static Scanner input = new Scanner(System.in);

    public static void posicoes(int x, int y) { 
    	if(casa[x][y] == 1) { // campo marcado pelo jogador 1 (X) 
    		System.out.print("X"); 
    	} else if(casa[x][y] == 2) { // campo marcado pelo jogador 2 (O)
    		System.out.print("O"); 
    	} else { // campo não marcado aparece em branco (“ ”) 
    		System.out.print(" "); 
    	} 
    }

    public static void tabuleiro() {
    	System.out.print("\n   1   2    3\n"); // nº colunas do tabuleiro 
    	System.out.print("1 "); // nº 1ª linha
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
    }

    public static void jogo(int jogador) { 
    	int x = 0; 
    	if (jogador == 1) { // definindo o jogador da vez
    		jogador = 1; 
    	} else { 
    		jogador = 2; 
    	} 
    	System.out.println("\n\nVez do Jogador "+jogador); 
    	while(x == 0) { 
    		l = 0; // inicializando a linha 
    		c = 0; // inicializando a coluna 
    		while(l < 1 || l > 3) { 
    			System.out.print("\t- Escolha a Linha (1,2,3): ");
    			l = input.nextInt(); 
    			if(l < 1 || l > 3) { 
    				System.out.println("Linha inválida! Escolha uma linha entre 1 e 3"); 
    			} 
    		} while(c < 1 || c > 3) { 
    			System.out.print("\t- Escolha a Coluna (1,2,3): "); 
    			c = input.nextInt(); 
    			if(c < 1 || c > 3) { 
    				System.out.println("Coluna invalida! Escolha uma coluna entre 1 e 3"); 
    			} 
    		}
    		l = l - 1; // ajuste dos valores das posições (1 é 0, 2 é 1...) 
    		c = c - 1; 
    		if(casa[l][c] == 0) { // posição vazia
    			casa[l][c] = jogador; // marcar com o símbolo do jogador da vez 
    			x = 1; // aumenta o contador para impedir de entrar no while novamente 
    		} else { 
    			System.out.println("Posição ocupada"); 
    		} 
    	} 
    }

    public static void check() { 
    	int x  = 0; 
    	// horizontal          
    	for(x = 0; x < 3; x++) { 
    		if(casa[x][0] == casa[x][1] && casa[x][0] == casa[x][2]) { 
    			if(casa[x][0] == 1) {
    				vencedor = 1; 
    			} if(casa[x][0] == 2) {
    				vencedor = 2; 
    			}
    		} 
    	} 
    	// vertical
    	for(x = 0; x < 3; x++) { 
    		if(casa[0][x] == casa[1][x] && casa[0][x] == casa[2][x]) { 
    			if(casa[0][x] == 1) {
    				vencedor = 1; 
    			} if(casa[0][x] == 2) {
    				vencedor = 2; 
    			}
    		} 
    	} 
    	// diagonal principal
    	if(casa[0][0] == casa[1][1] && casa[0][0] == casa[2][2]) { 
    		if(casa[0][0] == 1) {
    			vencedor = 1; 
    		} if(casa[0][0] == 2) {
    			vencedor = 2; 
    		}
    	} 
    	// diagonal secundária 
    	if(casa[0][2] == casa[1][1] && casa[0][2] == casa[2][0]) { 
    		if(casa[0][2] == 1) {
    			vencedor = 1; 
    		} if(casa[0][2] == 2) {
    			vencedor = 2; 
    		}
    	} 
    }

    public static void main(String[] args) { 
    	int x = 0; 
    	for (x = 0; x < 9; x++) { // 9 rodadas no jogo (9 posições)
    		tabuleiro(); 
    		if (x % 2 == 0) {  //par é o jogador 1
    			jogo(2); 
    		} else { // impar é o jogador 2
    			jogo(1); 
    		}
    		check();
    		if (vencedor == 1 || vencedor == 2) { 
    			break; // "i=10;" também funcionaria
    		} 
    	} 
    	tabuleiro(); 
    	System.out.println(); 
    	if (vencedor == 1 || vencedor == 2) { 
    		System.out.printf("\nJogador é o ganhador!",vencedor);
    	} else { 
    		System.out.println("\nNão houve vencedor, o jogo empatou"); 
    	} 
    }
}
