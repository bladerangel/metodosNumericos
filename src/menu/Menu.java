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
			System.out.println("\nEscolha dentre as op��es abaixo:");
			System.out.println("C - Convers�o");
			System.out.println("S - Sistema Linear");
			System.out.println("E - Equa��o Alg�brica");
			System.out.println("F - Finalizar\n");
			opcao = entrada.next();
			escolha();
		} while (!opcao.equalsIgnoreCase("F"));
	}

	public void conversao() {
		System.out.println("C - Convers�o");
		System.out.println("Digite um n�mero decimal:");
		double numeroDecimal = entrada.nextDouble();
		conversao.resolucao(numeroDecimal);
	}

	public void sistemaLinear() {
		System.out.println("S - Sistema Linear");
		System.out.println("Digite a quantidade de equa��es, em seguida digite os coeficiente e termos independentes:");
		int quantidadeEquacoes = entrada.nextInt();
		double[][] matriz = new double[quantidadeEquacoes][quantidadeEquacoes + 1];
		for (int i = 0; i < quantidadeEquacoes; i++) {
			for (int j = 0; j < quantidadeEquacoes + 1; j++) {
				matriz[i][j] = entrada.nextDouble();
			}
		}
		sistemaLinear.resolucao(matriz);
	}

	public void equacao() {
		System.out.println("E - Equa��o Alg�brica");
		System.out.println("Digite o grau, em seguida digite os coeficiente");
		int grau = entrada.nextInt();
		double[] equacao = new double[grau + 1];
		for (int i = equacao.length - 1; i >= 0; i--) {
			equacao[i] = entrada.nextDouble();
		}
		double a0 = equacao[0];
		if (a0 != 0)
			this.equacao.resolucao(equacao);
		else
			System.out.println("Voc� deve fornecer um a0 para a equa��o!");
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
			System.out.println("Op��o errada! Escolha novamente...");
			break;
		}

	}

}
