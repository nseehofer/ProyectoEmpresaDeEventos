package ar.unlam.edu.pb2.empresaDeEventos;

public class EventoInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventoInexistenteException(String mensaje) {
		super(mensaje);
	}

}
