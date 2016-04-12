package equacao;

public class Equacao {

	private Newton newton;
	private Lagrange lagrange;

	public Equacao() {
		newton = new Newton();
		lagrange = new Lagrange();
	}

	public void resolucao(double[] equacao) {
		newton.aproximacaoRaiz(equacao, lagrange.limites(equacao));
	}

}
