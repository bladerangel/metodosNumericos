import java.util.Scanner;

public class Menu {

	private String opcao;
	private Scanner entrada;
	private Conversao conversao;

	public Menu() {
		entrada = new Scanner(System.in);
		conversao = new Conversao();
	}

	public void mostrarOpcoes() {
		do {
			System.out.println("\nEscolha dentre as op��es abaixo:");
			System.out.println("C - Convers�o");
			System.out.println("S - Sistema Linear");
			System.out.println("E - Equa��o Alg�brica");
			System.out.println("F - Finalizar\n");
			opcao = entrada.next();
			escolha();
		} while (!opcao.equalsIgnoreCase("F"));

	}

	public void escolha() {
		switch (opcao.toUpperCase()) {
		case "C":
			System.out.println("C - Convers�o");
			System.out.println("C - Digite um n�mero decimal");
			int numeroDecimal = entrada.nextInt();
			conversao.metodoBinario(numeroDecimal);
			System.out.println();
			conversao.metodoOctal(numeroDecimal);
			System.out.println();
			conversao.metodoHexa(numeroDecimal);
			System.out.println();
			break;

		case "S":
			System.out.println("S - Sistema Linear");
			break;

		case "E":
			System.out.println("E - Equa��o Alg�brica");
			break;

		case "F":
			System.out.println("Vou saiu do programa!");
			break;
		default:
			System.out.println("Op��o errada! Escolha novamente...");
			break;
		}

	}

}
