package conversor;

public class Octal {
	
	public void resolucao(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Octal : " + numero);
			return;
		}
		quociente = numero / 8;
		resto = numero % 8;
		resolucao(quociente);
		System.out.print(resto);
	}

}
