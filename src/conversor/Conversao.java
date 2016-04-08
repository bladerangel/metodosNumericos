package conversor;

public class Conversao {

	private Binario binario;
	private Octal octal;
	private Hexadecimal hexadecimal;

	public Conversao() {
		binario = new Binario();
		octal = new Octal();
		hexadecimal = new Hexadecimal();
	}

	public void resolucao(int numeroDecimal) {
		binario.resolucao(numeroDecimal);
		System.out.println();
		octal.resolucao(numeroDecimal);
		System.out.println();
		hexadecimal.resolucao(numeroDecimal);
		System.out.println();
	}

}
