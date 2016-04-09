package equacao;

public class Equacao {

	private Newton newton;
	private Lagrange lagrange;

	public Equacao() {
		newton = new Newton();
		lagrange = new Lagrange();
	}

	public void resolucao(double[] equacao) {

		try {
			newton.aproximacaoRaiz(equacao, lagrange.raizes(equacao));
		} catch (Exception e) {
			System.out.println("Erro nos valores passados na equacao, motivos não atendidos:");
			System.out.println("1 - an > 0");
			System.out.println("2 - a0 != 0");
		}
	}

}
