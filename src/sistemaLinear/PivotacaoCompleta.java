package sistemaLinear;

public class PivotacaoCompleta {

	public static double EPSILON = Math.pow(10, -8);

	public void resolucao(double[][] matriz) {

		double[][] aux = new double[matriz.length][matriz.length + 1];
		double[] multiplicador = new double[matriz.length];
		int[] indexX = new int[matriz.length];
		int[] linhas = new int[matriz.length];
		for (int i = 0; i < indexX.length; i++) {
			indexX[i] = i;
			linhas[i] = -1;
		}
		for (int k = 0; k < matriz.length; k++) {
			int[] localizacao = maior(matriz, linhas);
			int linha = localizacao[0];
			int coluna = localizacao[1];
			linhas[linha] = linha;
			// System.out.println("localizacao: " + localizacao[0] + "--" +
			// localizacao[1]);

			if (k < matriz.length - 1) {
				for (int i = 0; i < multiplicador.length; i++) {
					if (i != linha) {
						multiplicador[i] = -matriz[i][coluna] / matriz[linha][coluna];
						// System.out.println(multiplicador[i]);
					}
				}

				for (int i = 0; i < matriz.length; i++) {
					for (int j = 0; j < matriz.length + 1; j++) {
						if (i != linha) {
							matriz[i][j] = multiplicador[i] * matriz[linha][j] + matriz[i][j];
						}
					}
				}
			}

			if (matriz[linha][coluna] == 0) {

				for (int i = 0; i < linhas.length; i++) {
					if (linhas[i] == -1) {
						linha = i;
						break;
					}
				}
			}

			for (int i = 0; i < matriz.length + 1; i++) {
				aux[k][i] = matriz[linha][i];
				matriz[linha][i] = 0;
			}
			if (coluna != k) {
				trocaColunas(matriz, coluna, k);
				trocaColunas(aux, coluna, k);
				int auxa = indexX[k];
				indexX[k] = indexX[coluna];
				indexX[coluna] = auxa;
			}

		}

		System.out.println("Matriz Triangularizada: ");
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length + 1; j++) {
				System.out.print(aux[i][j] + " ");
			}
			System.out.println();
		}

		resultado(aux, indexX);
	}

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

	public void trocaColunas(double[][] matriz, int coluna, int k) {
		for (int i = 0; i < matriz.length; i++) {
			double aux = matriz[i][k];
			matriz[i][k] = matriz[i][coluna];
			matriz[i][coluna] = aux;
		}
	}

	public void resultado(double[][] matriz, int[] indexX) {
		System.out.println("Solução:");
		double[] valorX = new double[matriz.length];
		double[] resultadosOrdenados = new double[matriz.length];
		for (int i = matriz.length - 1; i >= 0; i--) {
			double soma = 0;
			for (int j = i + 1; j < matriz.length; j++) {
				soma += matriz[i][j] * valorX[j];

			}
			if (Math.abs(matriz[i][i]) < EPSILON) {

				if (Math.abs(matriz[i][matriz.length] - soma) < EPSILON) {

					// x[i] é variável livre...
					System.out.println("Sistema indeterminado:");
					valorX[i] = 0;

				} else {
					// SL incompatível
					System.out.println("Sistema incompativel");
					return;

				}
			} else {
				valorX[i] = (matriz[i][matriz.length] - soma) / matriz[i][i];
			}

		}

		for (int index : indexX) {
			resultadosOrdenados[indexX[index]] = valorX[index];
		}

		for (int i = 0; i < indexX.length; i++) {
			System.out.println("valor de X" + (i + 1) + " = " + resultadosOrdenados[i]);
		}
	}

}
