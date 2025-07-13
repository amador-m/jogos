package jogoDaForca;

import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	public static void main(String[] args) {
		String[] palavras = {"pizza","tesouro","matrix","vampiro","guitarra","bicicleta","penne","ratatouille"};

		Random random = new Random(); 
		Scanner input = new Scanner(System.in);

		String palavra = palavras[random.nextInt(palavras.length)].toLowerCase();
		StringBuilder espacos = new StringBuilder();

		for(int i=0;i<palavra.length();i++) {
			espacos.append("_ "); // add o "_" ao final da String
		}

		int erros=0;
		int tentativas=6;

		while(erros<tentativas && espacos.indexOf("_")!=-1) {
			System.out.println("\nPalavra: " + espacos.toString());
			ForcaDesenho.desenhar(erros);
			System.out.print("\nDigite uma letra: ");
			String entrada = input.nextLine().toLowerCase();
			if (entrada.length() == 0) continue;
			String letra = entrada.substring(0, 1);
			boolean acertou = false;
			for (int i=0;i<palavra.length();i++) {
				if (palavra.substring(i, i + 1).equals(letra) && espacos.charAt(i * 2) == '_') {
				    espacos.setCharAt(i * 2, letra.charAt(0));
				    acertou = true;
				}
			}
			if (!acertou) {
				erros++;
				System.out.println("Letra incorreta! Tentativas restantes: " +(tentativas - erros));
			}
		}
		if (espacos.indexOf("_")==-1) {
			System.out.println("\nParabéns! A palavra era: " + palavra);
		} else {
			ForcaDesenho.desenhar(erros);
			System.out.println("\nVocê perdeu! A palavra era: " + palavra);
		}
	}
}

