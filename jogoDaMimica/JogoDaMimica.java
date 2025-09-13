package jogoDaMimica;

import java.util.ArrayList;
import java.util.Random;

public class JogoDaMimica {
	public static void main(String[] args) {
		ArrayList<String> mimicas = new ArrayList<>();
		mimicas.add("Desmaiar");
		mimicas.add("Elefante");
		mimicas.add("Tocar violão");
		mimicas.add("Andar de bicicleta");
		mimicas.add("Dançar frevo");
		mimicas.add("Dinossauro");
		mimicas.add("Fazer uma tatuagem");
		mimicas.add("Titanic");
		mimicas.add("Uma pessoa atrasada");
		mimicas.add("Girafa");

		String sorteio = sortearMimica(mimicas);
		System.out.print("\n A sua mímica é: " + sorteio);
	}

	public static String sortearMimica(ArrayList<String> lista) {
		Random aleatorio = new Random();
		int posicao = aleatorio.nextInt(lista.size());
		return lista.get(posicao);
	}

}
