package es.unican.ps.GestionHotel.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.businessLayer.IGestionReservaRemote;
import es.unican.ps.gestionHotel.domain.DatosCliente;
import es.unican.ps.gestionHotel.domain.DatosPago;
import es.unican.ps.gestionHotel.domain.ReservaTipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class GestionReservaBean implements Serializable {

	
	@EJB
	private IGestionReservaRemote gestionReserva;
	
	private DatosCliente dc;
	private DatosPago dp;
	private ArrayList<ReservaTipoHabitacion> tipos;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private String nombreHotel;
	private int numeroReserva;
	
	public GestionReservaBean () {}
	
	public String creaReserva() {
		numeroReserva = gestionReserva.creaReserva(dc, dp, tipos, fechaIni, fechaFin, nombreHotel);
		if (numeroReserva  == -1) {
			return null;
		}
		
		return "gestionReserva.xhtml";
	}

	public IGestionReservaRemote getGestionReserva() {
		return gestionReserva;
	}

	public void setGestionReserva(IGestionReservaRemote gestionReserva) {
		this.gestionReserva = gestionReserva;
	}

	public DatosCliente getDc() {
		return dc;
	}

	public void setDc(DatosCliente dc) {
		this.dc = dc;
	}

	public DatosPago getDp() {
		return dp;
	}

	public void setDp(DatosPago dp) {
		this.dp = dp;
	}

	public ArrayList<ReservaTipoHabitacion> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<ReservaTipoHabitacion> tipos) {
		this.tipos = tipos;
	}

	public LocalDate getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
	
	
	

}
