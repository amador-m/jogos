package jogoDaForca;

public class ForcaDesenho {
	public static void desenhar(int erros) {
	       System.out.println("\t _______");
	       System.out.println("\t |     |");
	       System.out.println("\t |     " + (erros >= 1 ? "O" : ""));
	       System.out.print("\t |    ");
	       if (erros >= 2) 
	        System.out.print("/");
	       if (erros >= 3) 
	        System.out.print("|");
	       if (erros >= 4) 
	        System.out.print("\\");
	       System.out.println();
	       System.out.print("\t |    ");
	       if (erros >= 5) 
	        System.out.print("/");
	       if (erros >= 6) 
	        System.out.print(" \\");
	       System.out.println("\n\t_|_");
	   }

}
