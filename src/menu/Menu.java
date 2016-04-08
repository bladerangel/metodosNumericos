package menu;
import java.util.Scanner;

import conversor.Conversao;
import equacao.Equacao;
import sistemaLinear.SistemaLinear;

public class Menu {

	private String opcao;
	private Scanner entrada;
	private Conversao conversao;
	private SistemaLinear sistemaLinear;
	private Equacao equacao;

	public Menu() {
		entrada = new Scanner(System.in);
		conversao = new Conversao();
		sistemaLinear = new SistemaLinear();
		equacao = new Equacao();
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

	public void conversao() {
		System.out.println("C - Conversão");
		System.out.println("Digite um número decimal:");
		double numeroDecimal = entrada.nextDouble();
		conversao.resolucao(numeroDecimal);
	}

	public void sistemaLinear() {
		System.out.println("S - Sistema Linear");
		System.out
				.println("Digite a quantidade de equações, em seguida digite os coeficiente e termos independentes:");
		int quantidadeEquacoes = entrada.nextInt();
		double[][] matriz = new double[quantidadeEquacoes][quantidadeEquacoes + 1];
		for (int i = 0; i < quantidadeEquacoes; i++) {
			for (int j = 0; j < quantidadeEquacoes + 1; j++) {
				matriz[i][j] = entrada.nextInt();
			}
		}
		sistemaLinear.resolucao(matriz);

	}

	public void equacao() {
		System.out.println("E - Equação Algébrica");
		System.out.println("Digite o grau, em seguida digite os coeficiente");
		int grau = entrada.nextInt();
		double[] equacao = new double[grau + 1];
		// System.out.println(equacao.length);
		for (int i = 0; i < equacao.length; i++) {
			equacao[i] = entrada.nextInt();
		}
		this.equacao.resolucao(grau, equacao);

	}

	public void escolha() {
		switch (opcao.toUpperCase()) {
		case "C":
			conversao();
			break;

		case "S":
			sistemaLinear();
			break;

		case "E":
			equacao();
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
