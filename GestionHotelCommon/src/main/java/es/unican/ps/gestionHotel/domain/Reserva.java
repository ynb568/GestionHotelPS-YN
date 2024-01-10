package es.unican.ps.gestionHotel.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Reservas")
public class Reserva {
	//valor autogenerado y autoincremental
	@Id
	@GeneratedValue
	private int id;
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double importe;
	@OneToOne
	@JoinColumn(name="cliente_fk")
	private DatosCliente cliente;
	@OneToOne
	@JoinColumn(name="tarjeta_fk")
	private DatosPago tarjeta;
	@OneToMany
	@JoinColumn(name="reserva_fk")
	private ArrayList<ReservaTipoHabitacion> tiposHabs;
	
	public Reserva() {}
	
	public Reserva (LocalDate fechaEntrada, LocalDate fechaSalida, double importe, DatosCliente cliente, DatosPago tarjeta, ArrayList<ReservaTipoHabitacion> tiposHabs) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.importe = importe;
		this.cliente = cliente;
		this.tarjeta = tarjeta;
		this.tiposHabs = tiposHabs;
	}
	

	public ArrayList<ReservaTipoHabitacion> getTiposHabs() {
		if (tiposHabs == null) {
			tiposHabs = new ArrayList<ReservaTipoHabitacion>();
		}
		return tiposHabs;
	}

	public DatosCliente getCliente() {
		return cliente;
	}

	public void setCliente(DatosCliente cliente) {
		this.cliente = cliente;
	}

	public DatosPago getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(DatosPago tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	
}
