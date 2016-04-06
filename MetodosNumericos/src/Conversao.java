
public class Conversao {

	public void metodoBinario(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Binário : " + numero);
			return;
		}
		quociente = numero / 2;
		resto = numero % 2;
		metodoBinario(quociente);
		System.out.print(resto);
	}

	public void metodoOctal(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Octal : " + numero);
			return;
		}
		quociente = numero / 8;
		resto = numero % 8;
		metodoOctal(quociente);
		System.out.print(resto);
	}

	public void metodoHexa(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Hexadecimal : " + numero);
			return;
		}
		quociente = numero / 16;
		resto = numero % 16;
		metodoHexa(quociente);
		switch (resto) {
		case 10:
			System.out.print("A");
			break;
		case 11:
			System.out.print("B");
			break;
		case 12:
			System.out.print("C");
			break;
		case 13:
			System.out.print("D");
			break;
		case 14:
			System.out.print("E");
			break;
		case 15:
			System.out.print("F");
			break;
		default:
			System.out.print(resto);
			break;
		}

	}

}
