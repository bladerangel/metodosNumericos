package sistemaLinear;

public class PivotacaoCompleta {

	public void resolucao(double[][] matriz) {

		double pivo = matriz[0][0];
		int linha = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (pivo < matriz[i][j]) {
					pivo = matriz[i][j];
					linha = i;
				}
			}

		}

		for (int i = 0; i < matriz.length + 1; i++) {
			double multiplicador = matriz[linha][i] / pivo;
			System.out.print(multiplicador);
		}
		System.out.println(pivo);
	}

}
