package equacao;

public class Lagrange {

	// retorna o valor aplicacando o teorema de lagrande
	public double teorema(double[] equacao) {
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
		return teorema(inverteSinalEquacao(equacao));
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
		double l = teorema(equacao);
		double l1 = teorema(inverteEquacao(equacao));
		double l2 = teorema(indicesImparesNegativosEquacao(equacao));
		double l3 = teorema(inverteEquacao(indicesImparesNegativosEquacao(equacao)));
		System.out.println("Raízes reais positiva:" + 1 / l1 + " <= E+ <= " + l);
		System.out.println("Raízes reais negativa:" + -l2 + " <= E- <= " + -1 / l3);
		return l;
	}
}
