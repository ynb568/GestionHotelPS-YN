package es.unican.ps.gestionHotel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TipoHabitaciones")
public class TipoHabitacion {
	@Id
	private String tipo;
	private double precioPorNoche;
	private boolean disponible;
	
	public TipoHabitacion() {}
	
	public TipoHabitacion (String tipo, double precioPorNoche, boolean disponible) {
		this.tipo = tipo;
		this.precioPorNoche = precioPorNoche;
		this.disponible = disponible;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecioPorNoche() {
		return precioPorNoche;
	}

	public void setPrecioPorNoche(double precioPorNoche) {
		this.precioPorNoche = precioPorNoche;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
