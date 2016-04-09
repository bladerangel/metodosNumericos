package equacao;

public class Lagrange {

	public double teorema(double[] equacao) throws Exception {
		int n = equacao.length - 1;
		double a0 = equacao[0];
		double an = equacao[n];
		double b = 0;
		int k = 0;

		if (an > 0 && a0 != 0) {
			for (int i = 0; i < n; i++) {
				if (equacao[i] < 0) {
					k = i;
				}
				if (equacao[i] < b) {
					b = equacao[i];
				}
			}
			// System.out.println(n + "--" + k + "--" + b + "--" + an);
			double formula = 1 + Math.pow(Math.abs(b) / an, (double) 1 / (n - k));
			// System.out.println(formula);
			return formula;
		}
		throw new Exception();
	}

	public double[] inverteEquacao(double[] equacao) {
		double[] equacaoInvertida = new double[equacao.length];
		for (int i = 0; i < equacao.length; i++) {
			equacaoInvertida[i] = equacao[equacao.length - 1 - i];
		}
		return equacaoInvertida;
	}

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

	public double raizes(double[] equacao) throws Exception {

		double l = teorema(equacao);
		// System.out.println(l);
		double l1 = teorema(inverteEquacao(equacao));
		// System.out.println(l1);
		double l2 = teorema(indicesImparesNegativosEquacao(equacao));
		// System.out.println(l2);
		double l3 = teorema(inverteEquacao(indicesImparesNegativosEquacao(equacao)));
		// System.out.println(l3);
		System.out.println("Raízes reais positiva:" + 1 / l1 + " <= E+ <= " + l);
		System.out.println("Raízes reais negativa:" + -l2 + " <= E- <= " + -1 / l3);
		return l;
	}

}
