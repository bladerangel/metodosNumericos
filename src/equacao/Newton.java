package equacao;

public class Newton {

	public void aproximacaoRaiz(double[] equacao, double limiteSuperior) {

		// System.out.println(limiteSuperior);
		double aproximacaoInicial = limiteSuperior;
		double erro = 0;
		for (int i = 0; i < 1000; i++) {
			double v = calculoEquacao(equacao, aproximacaoInicial);
			double v1 = calculoEquacao(derivadaEquacao(equacao), aproximacaoInicial);
			double novaAproximacao = aproximacaoInicial - (v / v1);
			erro = Math.abs(aproximacaoInicial - novaAproximacao);
			if (erro < Math.pow(10, -8))
				break;
			aproximacaoInicial = novaAproximacao;
			// System.out.println(aproximacaoInicial + "--" + erro);
		}

		System.out.println("Aproximação da raíz: " + aproximacaoInicial);

	}

	public double[] derivadaEquacao(double[] equacao) {
		double[] derivadaEquacao = new double[equacao.length - 1];
		for (int i = 0; i < derivadaEquacao.length; i++) {
			derivadaEquacao[i] = equacao[i] * (equacao.length - 1 - i);
			// System.out.println(derivadaEquacao[i]);
		}
		return derivadaEquacao;
	}

	public double calculoEquacao(double[] equacao, double x) {
		double valor = 0;
		for (int i = 0; i < equacao.length; i++) {
			valor += equacao[i] * Math.pow(x, equacao.length - 1 - i);
		}
		// System.out.println(valor);
		return valor;
	}

}
