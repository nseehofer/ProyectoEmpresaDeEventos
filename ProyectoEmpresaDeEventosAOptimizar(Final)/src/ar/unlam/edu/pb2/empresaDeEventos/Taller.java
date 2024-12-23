package ar.unlam.edu.pb2.empresaDeEventos;

public class Taller extends Evento {
	
	private static Integer cupoMaximo = 1;

	public static Integer getCupoMaximo() {
		return cupoMaximo;
	}

	public static void setCupoMaximo(Integer cupoMaximo) {
		Taller.cupoMaximo = cupoMaximo;
	}

}
