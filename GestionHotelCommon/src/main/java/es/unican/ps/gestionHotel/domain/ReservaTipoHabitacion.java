package es.unican.ps.gestionHotel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ReservaTipoHabitaciones")
public class ReservaTipoHabitacion {
	
	@Id
	@GeneratedValue
	private int id;
	private int numHabitaciones;
	@OneToOne
	@JoinColumn(name="tipoHabitacion_fk")
	private TipoHabitacion tipo;
	


	public ReservaTipoHabitacion (int numHabitaciones, TipoHabitacion tipo) {
		this.numHabitaciones = numHabitaciones;
		this.tipo = tipo;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

}
