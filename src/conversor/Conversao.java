package conversor;

public class Conversao {

	public void resolucao(double numeroDecimal) {
		System.out.println("Binário:" + base(numeroDecimal, 2));
		System.out.println("Octal:" + base(numeroDecimal, 8));
		System.out.println("Hexadecimal:" + base(numeroDecimal, 16));
	}

	// retorna o numero correspondente a base fornecida
	public String base(double numero, int base) {
		int quociente;
		int resto;
		int parteReal = (int) numero;
		double parteFracionaria = numero - parteReal;
		String conversao = "";
		if (parteReal < 0) {
			return "não é possível converter numeros negativos";
		}

		if (parteReal == 0) {
			conversao += "0";
		}

		// divisoes sucessivas
		while (parteReal > 0) {

			quociente = parteReal / base;
			resto = parteReal % base;
			parteReal = quociente;
			conversao += hexaLetras(resto);
		}
		if (parteReal != 0) {
			conversao += parteReal;
		}
		// inverte a ordem dos restos das divisoes
		conversao = new StringBuilder(conversao).reverse().toString();

		if (parteFracionaria != 0) {
			conversao += ",";
		}

		// multiplicacoes sucessivas
		while (parteFracionaria != 0) {
			parteFracionaria *= base;
			parteReal = (int) (parteFracionaria);
			parteFracionaria = parteFracionaria - parteReal;
			conversao += hexaLetras(parteReal);
		}
		return conversao;
	}

	// valores caso a base seja a hexadecimal
	public String hexaLetras(int resto) {
		switch (resto) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return String.valueOf(resto);
		}
	}

}
