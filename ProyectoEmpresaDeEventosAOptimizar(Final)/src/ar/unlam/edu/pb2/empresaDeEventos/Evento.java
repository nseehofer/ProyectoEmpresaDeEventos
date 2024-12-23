package ar.unlam.edu.pb2.empresaDeEventos;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
	
	private static Integer contadorID = 0; 
	private String id = "AB";
	private LocalDate fecha;

	
	public Evento() {
		this.id += (contadorID++).toString();
		
	}

	
	public String getId() {
		return this.id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public int compareTo(Evento o) {
		return this.id.compareTo(o.id);
	}


	
	


	
	



	
	
}
