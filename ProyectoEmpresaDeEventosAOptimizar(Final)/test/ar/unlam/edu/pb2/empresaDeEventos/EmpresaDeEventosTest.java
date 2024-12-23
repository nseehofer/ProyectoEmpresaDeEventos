package ar.unlam.edu.pb2.empresaDeEventos;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EmpresaDeEventosTest {

	@Test
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso() {

		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Persona cliente = new Cliente("Ricardo", "Alvarez", 25632156);
		assertTrue(empresa.agregarCliente(cliente));
	}

	@Test(expected = EventoDuplicadoException.class)
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnEventoExistenteObtengoUnaEventoDuplicadoException()
			throws EventoDuplicadoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Conferencia();
		Evento evento2 = new Taller();
		Evento evento3 = evento1;

		assertTrue(empresa.agregarEvento(evento1));
		assertTrue(empresa.agregarEvento(evento2));
		empresa.agregarEvento(evento3);

	}

	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoBuscoUnEventoExistentePorSuCodigoObtengoElEvento()
			throws EventoDuplicadoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Conferencia();
		Evento evento2 = new Taller();
		Evento evento3 = new Conferencia();
		Evento evento4 = new Taller();
		Evento evento5 = new Conferencia();

		assertTrue(empresa.agregarEvento(evento1));
		assertTrue(empresa.agregarEvento(evento2));
		assertTrue(empresa.agregarEvento(evento3));
		assertTrue(empresa.agregarEvento(evento4));
		assertTrue(empresa.agregarEvento(evento5));

		Evento resultadoEsperado = evento3;
		Evento resultadoObtenido = empresa.buscarEvento(evento3.getId());
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoVerificoSiUnClienteSeEncuentraEntreLosParticipantesDeUnEventoPorClienteYExisteObtengoUnResultadoPositivo()
			throws EventoDuplicadoException, EventoInexistenteException, PersonaAusenteException, CupoMaximoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Conferencia();
		Persona cliente = new Cliente("Ricardo", "Alvarez", 25632156);
		Persona cliente2 = new Cliente("Juan", "Pino", 15785632);
		Persona cliente3 = new Cliente("Anastacia", "Olivar", 41256326);
		Persona cliente4 = new Cliente("Alvaro", "Perez", 25632326);
		Persona cliente5 = new Cliente("Malena", "Gutierrez", 25662156);

		HashSet<Persona> personasAAsignar = new HashSet<Persona>();
		personasAAsignar.add(cliente);
		personasAAsignar.add(cliente2);
		personasAAsignar.add(cliente3);
		personasAAsignar.add(cliente4);
		personasAAsignar.add(cliente5);

		assertTrue(empresa.agregarEvento(evento1));
		empresa.asignarPersonasAEvento(evento1, personasAAsignar);
		assertTrue(empresa.seEncuentraEsteParticipanteEnElSiguienteEvento(evento1, cliente2));
	}

	@Test(expected = PersonaPresenteException.class)
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnEventoDondeExisteElClienteObtengoUnaClienteExistenteEnEventoException()
			throws EventoDuplicadoException, EventoInexistenteException, PersonaAusenteException,
			PersonaPresenteException, ListaInexistenteException, CupoMaximoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Conferencia();
		Persona cliente = new Cliente("Ricardo", "Alvarez", 25632156);
		Persona cliente2 = new Cliente("Juan", "Pino", 15785632);
		Persona cliente3 = new Cliente("Anastacia", "Olivar", 41256326);

		HashSet<Persona> personasAAsignar = new HashSet<Persona>();
		personasAAsignar.add(cliente);
		personasAAsignar.add(cliente2);

		assertTrue(empresa.agregarEvento(evento1));
		empresa.asignarPersonasAEvento(evento1, personasAAsignar);

		empresa.asignarUnaPersonaAlEvento(evento1, cliente3);
		empresa.asignarUnaPersonaAlEvento(evento1, cliente2);

	}

	@Test(expected = CupoMaximoException.class)
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnTallerSinCupoDondeNoExisteElClienteObtengoUnResultadoFallido()
			throws EventoDuplicadoException, PersonaAusenteException, PersonaPresenteException,
			ListaInexistenteException, CupoMaximoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Taller();
		Persona cliente = new Cliente("Ricardo", "Alvarez", 25632156);
		Persona cliente2 = new Cliente("Juan", "Pino", 15785632);
		assertTrue(empresa.agregarEvento(evento1));
		empresa.asignarUnaPersonaAlEvento(evento1, cliente);
		empresa.asignarUnaPersonaAlEvento(evento1, cliente2);
	}

	@Test
	public void dadoQueExisteUnaEmpresaConEventos3ConferenciasObtengoUnaListaCon3Conferencias()
			throws EventoDuplicadoException {
		EmpresaDeEventos empresa = new EmpresaDeEventos();
		Evento evento1 = new Taller();
		Evento evento2 = new Conferencia();
		Evento evento3 = new Conferencia();
		Evento evento4 = new Taller();
		Evento evento5 = new Conferencia();
		empresa.agregarEvento(evento1);
		empresa.agregarEvento(evento2);
		empresa.agregarEvento(evento3);
		empresa.agregarEvento(evento4);
		empresa.agregarEvento(evento5);

		Set<Evento> eventosConferencia = new HashSet<Evento>();
		eventosConferencia.add(evento2);
		eventosConferencia.add(evento3);
		eventosConferencia.add(evento5);
		

		Set<Evento> resultadoEsperado = eventosConferencia;
		Set<Evento> resultadoObtenido = empresa.obtenerEventosConferencia();
		
		assertEquals(resultadoEsperado,resultadoObtenido);

	}

}
