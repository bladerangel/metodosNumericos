package equacao;

public class Newton {

	public void aproximacaoRaiz(double[] equacao, double limiteSuperior) {

		// System.out.println(limiteSuperior);
		double aproximacaoInicial = limiteSuperior;
		double erro = 0;
		double[] equacaoDerivada = derivadaEquacao(equacao);
		for (int i = 0; i < 1000; i++) {
			double v = calculoEquacao(equacao, aproximacaoInicial);
			double v1 = calculoEquacao(equacaoDerivada, aproximacaoInicial);
			double novaAproximacao = aproximacaoInicial - (v / v1);
			erro = Math.abs(aproximacaoInicial - novaAproximacao);
			if (erro < Math.pow(10, -8))
				break;
			aproximacaoInicial = novaAproximacao;
			// System.out.println(aproximacaoInicial);
		}

		System.out.println("Aproxima��o da ra�z: " + aproximacaoInicial);

	}

	public double[] derivadaEquacao(double[] equacao) {
		double[] derivadaEquacao = new double[equacao.length - 1];
		for (int i = 0; i < derivadaEquacao.length; i++) {
			derivadaEquacao[i] = equacao[i + 1] * (i + 1);
			// System.out.println(derivadaEquacao[i]);
		}
		return derivadaEquacao;
	}

	public double calculoEquacao(double[] equacao, double x) {
		double valor = 0;
		for (int i = 0; i < equacao.length; i++) {
			valor += equacao[i] * Math.pow(x, i);
		}
		// System.out.println(valor);
		return valor;
	}

}
