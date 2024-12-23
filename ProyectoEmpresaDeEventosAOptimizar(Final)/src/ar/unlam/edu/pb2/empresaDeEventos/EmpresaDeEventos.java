package ar.unlam.edu.pb2.empresaDeEventos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EmpresaDeEventos {

	private Set<Persona> registroDeClientes = new HashSet<Persona>();
	private Set<Evento> registroDeEvento = new HashSet<Evento>();
	private Map<Evento, HashSet<Persona>> eventosRealizados = new TreeMap<Evento, HashSet<Persona>>();

	public Boolean agregarCliente(Persona cliente) {
		return this.registroDeClientes.add(cliente);
	}

	public Boolean agregarEvento(Evento evento) throws EventoDuplicadoException {
		if (this.registroDeEvento.contains(evento)) {
			throw new EventoDuplicadoException("Ya contamos con ese evento registrado");
		} else {

			return this.registroDeEvento.add(evento);
		}

	}

	public Evento buscarEvento(String id) {
		Evento eventoEncontrado = null;
		Iterator<Evento> iteradorEvento = this.registroDeEvento.iterator();

		while (iteradorEvento.hasNext()) {
			eventoEncontrado = iteradorEvento.next();
			if (eventoEncontrado.getId().equals(id)) {
				return eventoEncontrado;
			}
		}
		return eventoEncontrado;

	}

	public void asignarPersonasAEvento(Evento evento, HashSet<Persona> personasAAsignar)
			throws EventoInexistenteException, CupoMaximoException {
		if (buscarEvento(evento.getId()) != null && evento.getClass().getSimpleName().equals("Taller")
				&& personasAAsignar.size() >= Taller.getCupoMaximo()) {
			throw new CupoMaximoException("El taller agoto los cupos disponibles");
		} else if (buscarEvento(evento.getId()) != null) {
			this.eventosRealizados.put(evento, personasAAsignar);
		} else {
			throw new EventoInexistenteException("No existe tal evento");
		}

	}

	public Boolean seEncuentraEsteParticipanteEnElSiguienteEvento(Evento evento, Persona cliente)
			throws PersonaAusenteException {
		HashSet<Persona> listaDePersonas = this.eventosRealizados.get(evento);
		if (listaDePersonas != null) {
			Iterator<Persona> iteradorListaDePersonas = listaDePersonas.iterator();
			while (iteradorListaDePersonas.hasNext()) {
				Persona personaEncontrada = iteradorListaDePersonas.next();
				if (personaEncontrada.equals(cliente)) {
					return true;
				}
			}
		}
		return false;
	}

	public void asignarUnaPersonaAlEvento(Evento evento, Persona cliente)
			throws PersonaAusenteException, PersonaPresenteException, ListaInexistenteException, CupoMaximoException {
		HashSet<Persona> listaDePersonas = this.eventosRealizados.get(evento);
		if (seEncuentraEsteParticipanteEnElSiguienteEvento(evento, cliente)) {
			throw new PersonaPresenteException("Esta persona ya se encuentra en el evento");
		} else if (listaDePersonas != null && evento.getClass().getSimpleName().equals("Taller")
				&& listaDePersonas.size() >= Taller.getCupoMaximo()) {
			throw new CupoMaximoException("El taller agoto los cupos disponibles");
		} else if (listaDePersonas == null) {
			listaDePersonas = new HashSet<Persona>();
			listaDePersonas.add(cliente);
			this.eventosRealizados.put(evento, listaDePersonas);
		} else {
			listaDePersonas.add(cliente);
			this.eventosRealizados.put(evento, listaDePersonas);
		}
	}

	public Set<Evento> obtenerEventosConferencia() {
		// CREO VARIABLE INSTANCIADA
		Set<Evento> eventosConferenciaExistentes = new HashSet<Evento>();
		Iterator<Evento> iteradorDeEventosExistentes = this.registroDeEvento.iterator();
		Evento eventoAEvaluar = null;
		while (iteradorDeEventosExistentes.hasNext()) {
			eventoAEvaluar = iteradorDeEventosExistentes.next();
			if (eventoAEvaluar.getClass().getSimpleName().equals("Conferencia")) {
				eventosConferenciaExistentes.add(eventoAEvaluar);
			}

		}

		return eventosConferenciaExistentes;
	}
}
