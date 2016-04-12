
import java.util.Scanner;

public class ep1 {

	public static double EPSILON = Math.pow(10, -8);
	private String opcao;
	private Scanner entrada;

	public ep1() {
		entrada = new Scanner(System.in);
	}

	// ----------------------- Menu ---------------------------

	// mostra as opcoes que o usuario pode escolher
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
			conversaoEntrada();
			break;
		case "S":
			sistemaLinearEntrada();
			break;
		case "E":
			equacaoEntrada();
			break;
		case "F":
			System.out.println("Vou saiu do programa!");
			break;
		default:
			System.out.println("Opção errada! Escolha novamente...");
			break;
		}
	}

	// escolhida a opcao conversao
	public void conversaoEntrada() {
		System.out.println("C - Conversão");
		System.out.println("Digite um número decimal:");
		double numeroDecimal = entrada.nextDouble();
		resolucaoConversao(numeroDecimal);
	}

	// escolhida a opcao sistema linear
	public void sistemaLinearEntrada() {
		System.out.println("S - Sistema Linear");
		System.out.println("Digite a quantidade de equações, em seguida digite os coeficiente e termos independentes:");
		int quantidadeEquacoes = entrada.nextInt();
		double[][] matriz = new double[quantidadeEquacoes][quantidadeEquacoes + 1];
		for (int i = 0; i < quantidadeEquacoes; i++) {
			for (int j = 0; j < quantidadeEquacoes + 1; j++) {
				matriz[i][j] = entrada.nextDouble();
			}
		}
		resolucaoSistemaLinear(matriz);
	}

	// escolhida a opcao equacao
	public void equacaoEntrada() {
		System.out.println("E - Equação Algébrica");
		System.out.println("Digite o grau, em seguida digite os coeficiente");
		int grau = entrada.nextInt();
		double[] equacao = new double[grau + 1];
		for (int i = equacao.length - 1; i >= 0; i--) {
			equacao[i] = entrada.nextDouble();
		}
		double a0 = equacao[0];
		if (a0 != 0)
			resolucaoEquacao(equacao);
		else
			System.out.println("Você deve fornecer um a0 para a equação!");
	}

	// ----------------------- Conversao ---------------------------
	public void resolucaoConversao(double numeroDecimal) {
		System.out.println("Binário:" + base(numeroDecimal, 2));
		System.out.println("Octal:" + base(numeroDecimal, 8));
		System.out.println("Hexadecimal:" + base(numeroDecimal, 16));
	}

	// retorna o numero correspondente a base fornecida
	public String base(double numero, int base) {
		int quociente;
		int resto;
		int parteReal = (int) numero;
		double parteFracionaria = numero - parteReal;
		String conversao = "";
		if (parteReal < 0) {
			return "não é possível converter numeros negativos";
		}

		if (parteReal == 0) {
			conversao += "0";
		}

		// divisoes sucessivas
		while (parteReal > 0) {

			quociente = parteReal / base;
			resto = parteReal % base;
			parteReal = quociente;
			conversao += hexaLetras(resto);
		}
		if (parteReal != 0) {
			conversao += parteReal;
		}
		// inverte a ordem dos restos das divisoes
		conversao = new StringBuilder(conversao).reverse().toString();

		if (parteFracionaria != 0) {
			conversao += ",";
		}

		// multiplicacoes sucessivas
		while (parteFracionaria != 0) {
			parteFracionaria *= base;
			parteReal = (int) (parteFracionaria);
			parteFracionaria = parteFracionaria - parteReal;
			conversao += hexaLetras(parteReal);
		}
		return conversao;
	}

	// valores caso a base seja a hexadecimal
	public String hexaLetras(int resto) {
		switch (resto) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return String.valueOf(resto);
		}
	}

	// ----------------------- Sistema Linear ---------------------------
	public void resolucaoSistemaLinear(double[][] matriz) {
		switch (solucao(matriz)) {
		case 0:
			System.out.println("Sistema compativel.");
			break;
		case 1:
			System.out.println("Sistema indeterminado.");
			break;
		case 2:
			System.out.println("Sistema incompativel.");
			break;
		default:
			break;
		}
	}

	// retorna 0 se o sistema é compatível
	// retorna 1 se o sistema é indeterminado
	// retorna 2 se o sistema é incompatível
	public int solucao(double[][] matriz) {
		double[][] aux = new double[matriz.length][matriz.length + 1];
		double[] multiplicador = new double[matriz.length];
		int[] indexX = new int[matriz.length];// indices das variaveis
		int[] linhas = new int[matriz.length];// linhas utilizada
		for (int i = 0; i < indexX.length; i++) {
			indexX[i] = i;
			linhas[i] = -1;// linhas não utilizadas
		}
		for (int k = 0; k < matriz.length; k++) {
			int[] localizacao = maior(matriz, linhas);
			int linha = localizacao[0];
			int coluna = localizacao[1];

			if (k < matriz.length - 1) {
				multiplica(matriz, multiplicador, linha, coluna);
			}

			// caso o pivo encontrado seja nulo
			if (Math.abs(matriz[linha][coluna]) < EPSILON) {
				for (int i = 0; i < linhas.length; i++) {
					if (linhas[i] == -1) {
						linha = i;
						coluna = k;
						break;
					}
				}
			}
			// armazena a indice da linha utilizada
			linhas[linha] = linha;

			// armazena a linha da matriz utilizada na matriz auxiliar
			for (int i = 0; i < matriz.length + 1; i++) {
				aux[k][i] = matriz[linha][i];
				matriz[linha][i] = 0;
			}

			// troca coluna afim de triangularizar a matriz
			if (coluna != k) {
				trocaColunas(matriz, coluna, k);
				trocaColunas(aux, coluna, k);
				trocaIndex(indexX, coluna, k);
			}

		}
		mostrarMatrizTriangularizada(aux);
		return substituicoesRetroativas(aux, indexX);
	}

	// devolve a localizacao do maior valor encontrado na matriz
	public int[] maior(double[][] matriz, int[] linhas) {
		double maior = 0;
		int[] localizacao = new int[2];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (Math.abs(matriz[i][j]) > maior) {
					maior = Math.abs(matriz[i][j]);
					localizacao[0] = i;
					localizacao[1] = j;
				}
			}
		}
		return localizacao;
	}

	// calculo dos multiplicadores e as operacoes de soma
	public void multiplica(double[][] matriz, double[] multiplicador, int linha, int coluna) {
		if (Math.abs(matriz[linha][coluna]) >= EPSILON) {
			for (int i = 0; i < multiplicador.length; i++) {
				if (i != linha) {
					multiplicador[i] = -matriz[i][coluna] / matriz[linha][coluna];
				}
			}
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length + 1; j++) {
					if (i != linha && Math.abs(multiplicador[i]) >= EPSILON) {
						matriz[i][j] = multiplicador[i] * matriz[linha][j] + matriz[i][j];
					}
				}
			}
		}
	}

	// troca de colunas da matriz
	public void trocaColunas(double[][] matriz, int coluna, int k) {
		for (int i = 0; i < matriz.length; i++) {
			double aux = matriz[i][k];
			matriz[i][k] = matriz[i][coluna];
			matriz[i][coluna] = aux;
		}
	}

	// troca dos valores dos indices das variaveis
	public void trocaIndex(int[] indexX, int coluna, int k) {
		int auxa = indexX[k];
		indexX[k] = indexX[coluna];
		indexX[coluna] = auxa;
	}

	public void mostrarMatrizTriangularizada(double[][] aux) {
		System.out.println("Matriz Triangularizada: ");
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length + 1; j++) {
				System.out.print(aux[i][j] + " ");
			}
			System.out.println();
		}
	}

	// encontrando os valores das variaveis aplicando substituicoes retroativas
	public int substituicoesRetroativas(double[][] matriz, int[] indexX) {
		System.out.println("Solução:");
		int tipo = 0;
		double[] valorX = new double[matriz.length];
		double[] resultadosOrdenados = new double[matriz.length];
		for (int i = matriz.length - 1; i >= 0; i--) {
			double soma = 0;
			for (int j = i + 1; j < matriz.length; j++) {
				soma += matriz[i][j] * valorX[j];
			}
			if (Math.abs(matriz[i][i]) < EPSILON) {
				if (Math.abs(matriz[i][matriz.length] - soma) < EPSILON) {
					System.out.println("Variaveis Livres : X" + (indexX[i] + 1));
					valorX[i] = 0;// atribui a variavel livre o valor 0
					tipo = 1;
				} else {
					return 2;
				}
			} else {
				valorX[i] = (matriz[i][matriz.length] - soma) / matriz[i][i];
			}

		}
		// mostras as variaveis em ondem crescente
		for (int index : indexX) {
			resultadosOrdenados[indexX[index]] = valorX[index];
		}

		for (int i = 0; i < indexX.length; i++) {
			System.out.println("valor de X" + (i + 1) + " = " + resultadosOrdenados[i]);
		}
		return tipo;
	}

	// ----------------------- Equacao ---------------------------
	public void resolucaoEquacao(double[] equacao) {
		metodoNewton(equacao, limites(equacao));
	}

	// retorna o valor aplicacando o teorema de lagrange
	public double teoremaLagrange(double[] equacao) {
		int n = equacao.length - 1;
		double an = equacao[n];
		double b = 0;
		int k = 0;
		if (an > 0) {
			for (int i = 0; i < n; i++) {
				if (equacao[i] < 0) {
					k = i;
				}
				if (equacao[i] < b) {
					b = equacao[i];
				}
			}
			double formula = 1 + Math.pow(Math.abs(b) / an, (double) 1 / (n - k));
			return formula;
		}
		return teoremaLagrange(inverteSinalEquacao(equacao));
	}

	// inverte a ordem da equacao
	public double[] inverteEquacao(double[] equacao) {
		double[] equacaoInvertida = new double[equacao.length];
		for (int i = 0; i < equacao.length; i++) {
			equacaoInvertida[i] = equacao[equacao.length - 1 - i];
		}
		return equacaoInvertida;
	}

	// troca o sinal dos expoentes impares da equacao
	public double[] indicesImparesNegativosEquacao(double[] equacao) {
		double[] equacaoNegativaIndicesImpares = new double[equacao.length];
		for (int i = 0; i < equacao.length; i++) {
			if (i % 2 != 0)
				equacaoNegativaIndicesImpares[i] = -equacao[i];
			else
				equacaoNegativaIndicesImpares[i] = equacao[i];
		}
		return equacaoNegativaIndicesImpares;
	}

	// troca o sinal da equacao
	public double[] inverteSinalEquacao(double[] equacao) {
		double[] equacaoSinalInvertido = new double[equacao.length];
		for (int i = 0; i < equacao.length; i++) {
			equacaoSinalInvertido[i] = -equacao[i];

		}
		return equacaoSinalInvertido;
	}

	// devolve o limite superior para ser utilizada no metodo de newton
	public double limites(double[] equacao) {
		double l = teoremaLagrange(equacao);
		double l1 = teoremaLagrange(inverteEquacao(equacao));
		double l2 = teoremaLagrange(indicesImparesNegativosEquacao(equacao));
		double l3 = teoremaLagrange(inverteEquacao(indicesImparesNegativosEquacao(equacao)));
		System.out.println("Raízes reais positiva:" + 1 / l1 + " <= E+ <= " + l);
		System.out.println("Raízes reais negativa:" + -l2 + " <= E- <= " + -1 / l3);
		return l;
	}

	// calcula a aproximacao de uma raiz usando o limite superior da equacao
	public void metodoNewton(double[] equacao, double limiteSuperior) {

		double aproximacaoInicial = limiteSuperior;
		double erro = 0;
		double[] equacaoDerivada = derivadaEquacao(equacao);
		for (int i = 0; i < 1000; i++) {
			double v = calculoEquacao(equacao, aproximacaoInicial);
			double v1 = calculoEquacao(equacaoDerivada, aproximacaoInicial);
			double novaAproximacao = aproximacaoInicial - (v / v1);
			erro = Math.abs(aproximacaoInicial - novaAproximacao);
			if (erro < EPSILON)
				break;
			aproximacaoInicial = novaAproximacao;
		}

		System.out.println("Aproximação da raíz: " + aproximacaoInicial);

	}

	// retorna a equacao derivada
	public double[] derivadaEquacao(double[] equacao) {
		double[] derivadaEquacao = new double[equacao.length - 1];
		for (int i = 0; i < derivadaEquacao.length; i++) {
			derivadaEquacao[i] = equacao[i + 1] * (i + 1);
		}
		return derivadaEquacao;
	}

	// retorna o calculo da equacao usando um valor para x
	public double calculoEquacao(double[] equacao, double x) {
		double valor = 0;
		for (int i = 0; i < equacao.length; i++) {
			valor += equacao[i] * Math.pow(x, i);
		}
		return valor;
	}

	// ----------------------- Main ---------------------------
	public static void main(String[] args) {
		ep1 menu = new ep1();
		menu.mostrarOpcoes();
	}

}
