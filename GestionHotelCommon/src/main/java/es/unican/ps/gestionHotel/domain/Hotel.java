package es.unican.ps.gestionHotel.domain;

import java.util.ArrayList;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Hoteles")
public class Hotel {
	@Id
	private String nombre;
	private String direccion;
	private String localidad;
	@OneToMany
	@JoinColumn(name="hotel_fk")
	private ArrayList<TipoHabitacion> habitaciones;
	@OneToMany
	@JoinColumn(name="hotel_fk")
	private ArrayList<Reserva> reservas;
	
	public Hotel() {}

	public Hotel (String nombre, String direccion, String localidad, ArrayList<TipoHabitacion> habitaciones, ArrayList<Reserva> reservas) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.habitaciones = habitaciones;
		this.reservas = reservas;
	}
	
	public ArrayList<TipoHabitacion> getHabitaciones() {
		if (habitaciones == null) {
			habitaciones = new ArrayList<TipoHabitacion>();
		}
		return habitaciones;
	}

	public ArrayList<Reserva> getReservas() {
		if (reservas == null) {
			reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
