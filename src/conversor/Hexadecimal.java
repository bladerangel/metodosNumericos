package conversor;

public class Hexadecimal {

	public void resolucao(int numero) {
		int quociente;
		int resto;
		if (numero <= 1) {
			System.out.print("Hexadecimal : " + numero);
			return;
		}
		quociente = numero / 16;
		resto = numero % 16;
		resolucao(quociente);
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
