package equacao;

public class Newton {

	// calcula a aproximacao de uma raiz usando o limite superior da equacao
	public void aproximacaoRaiz(double[] equacao, double limiteSuperior) {

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

}
