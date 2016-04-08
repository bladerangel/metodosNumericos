package conversor;

public class Binario {
	
	public void resolucao(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Binário : " + numero);
			return;
		}
		quociente = numero / 2;
		resto = numero % 2;
		resolucao(quociente);
		System.out.print(resto);
	}

}
