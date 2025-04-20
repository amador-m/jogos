package jogos;

import java.util.Random;
import java.util.Scanner;

public class jogoDaVelhaCPU {
	private static int jogador;
    private static int [][] casa = new int[3][3];
    private static int l, c, vencedor;
    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    
    public static void posicoes(int x, int y) { 
    	if(casa[x][y] == 1) { 
    		System.out.print("X"); 
    	} else if(casa[x][y] == 2) { 
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
    }
    
    public static void jogadorHumano() {
        jogador = 1;
        System.out.println("\n\nSua vez (Jogador 1: X)");
        int x = 0;
        while(x == 0) {
            l = 0;
            c = 0;
            while(l < 1 || l > 3) {
                System.out.print("\t- Escolha a Linha (1,2,3): ");
                l = input.nextInt();
            }
            while(c < 1 || c > 3) {
                System.out.print("\t- Escolha a Coluna (1,2,3): ");
                c = input.nextInt();
            }
            l -= 1;
            c -= 1;
            if(casa[l][c] == 0) {
                casa[l][c] = jogador;
                x = 1; // pra sair do laço
            } else {
                System.out.println("Posição ocupada! Tente novamente");
            }
        }
    }
    
    public static void jogarCPU() {
        jogador = 2;
        System.out.println("\n\nVez da CPU (Jogador 2: O)");
        int i = 0;
        while(i == 0) {
            int l = random.nextInt(3);
            int c = random.nextInt(3);
            if(casa[l][c] == 0) {
                casa[l][c] = jogador;
                i = 1;
                System.out.println("CPU jogou na linha " + (l+1) + " e coluna " + (c+1));
            }
        }
    }
    
    public static void check() { 
    	int x = 0; 
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
    
    public static void limparTabuleiro() {
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                casa[x][y] = 0;
            }
        }
        vencedor = 0; // sem vencedores
    }
    
    public static boolean desejaReiniciar() {
        System.out.print("\nDeseja jogar novamente? (S/N): ");
        String resposta = input.next();
        return resposta.equalsIgnoreCase("S");
    }
    
    public static void main(String[] args) {
        boolean jogarNovamente = true;
        while (jogarNovamente) {
            limparTabuleiro();
            for(int i = 0; i < 9; i++) { // 9 posições -> 9 rodadas
                tabuleiro();
                if(i % 2 == 0) { 
                    jogadorHumano();
                } else {
                    jogarCPU();
                }
                check();
                if(vencedor == 1 || vencedor == 2) {
                    break; 
                }
            }
            tabuleiro();
            if(vencedor == 1 || vencedor == 2) {
                if(vencedor == 1) {
                	System.out.println("\nVocê venceu!");
                }
                else if(vencedor == 2) {
                    System.out.println("\nA CPU venceu, tente novamente");
                }
            } else {
                System.out.println("\nO jogo empatou");
            }
            jogarNovamente = desejaReiniciar();
        }
    }

}
