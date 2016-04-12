package sistemaLinear;

public class PivotacaoCompleta {

	public static double EPSILON = Math.pow(10, -8);

	// retorna 0 se o sistema é compatível
	// retorna 1 se o sistema é indeterminado
	// retorna 2 se o sistema é incompatível
	public int resolucao(double[][] matriz) {
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

	// encontrando os valores das variaveis aplicando substituicoes Retroativas
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

}
