package sistemaLinear;

public class SistemaLinear {

	private PivotacaoCompleta pivotacaoCompleta;

	public SistemaLinear() {
		pivotacaoCompleta = new PivotacaoCompleta();
	}

	public void resolucao(double[][] matriz) {
		switch (pivotacaoCompleta.resolucao(matriz)) {
		case 0:
			System.out.println("Sistema compativel");
			break;
		case 1:
			System.out.println("Sistema indeterminado");
			break;
		case 2:
			System.out.println("Sistema incompativel");
			break;
		default:
			break;
		}
	}
}
