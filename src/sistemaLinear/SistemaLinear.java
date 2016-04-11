package sistemaLinear;

public class SistemaLinear {

	private PivotacaoCompleta pivotacaoCompleta;

	public SistemaLinear() {
		pivotacaoCompleta = new PivotacaoCompleta();
	}

	public void resolucao(double[][] matriz) {
		pivotacaoCompleta.resolucao(matriz);
	}
}
