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
			System.out.println("\nEscolha dentre as opções abaixo:");
			System.out.println("C - Conversão");
			System.out.println("S - Sistema Linear");
			System.out.println("E - Equação Algébrica");
			System.out.println("F - Finalizar\n");
			opcao = entrada.next();
			escolha();
		} while (!opcao.equalsIgnoreCase("F"));

	}

	public void escolha() {
		switch (opcao.toUpperCase()) {
		case "C":
			System.out.println("C - Conversão");
			System.out.println("C - Digite um número decimal");
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
			System.out.println("E - Equação Algébrica");
			break;

		case "F":
			System.out.println("Vou saiu do programa!");
			break;
		default:
			System.out.println("Opção errada! Escolha novamente...");
			break;
		}

	}

}
