package equacao;

public class Lagrange {

	public double teorema(double[] equacao) throws Exception {
		double b = equacao[equacao.length - 1];
		double an = equacao[0];
		double n = equacao.length - 1;
		double k = 0;

		if (equacao[0] > 0 && equacao[equacao.length - 1] != 0) {

			for (int i = equacao.length - 1; i >= 0; i--) {
				if (equacao[i] < 0) {
					k = n - i;
				}
				if (b >= equacao[i]) {
					b = equacao[i];
				}

			}
			// System.out.println(n + "--" + k + "--" + b + "--" + an);
			double formula = 1 + Math.pow(Math.abs(b) / an, 1 / (n - k));
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
