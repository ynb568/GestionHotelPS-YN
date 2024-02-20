package es.unican.ps.GestionHotel.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import es.unican.ps.gestionHotel.businessLayer.IConsultaReservaRemote;
import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.ReservaTipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ConsultaReservaBean implements Serializable {

	@EJB
	private IConsultaReservaRemote consultaReserva;

	private String nomHotel = null;
	private String localidad = null;
	private ArrayList<Hotel> listaHoteles = new ArrayList<Hotel>();

	private Hotel h;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private HashSet<ReservaTipoHabitacion> listaTiposHabs = new HashSet<ReservaTipoHabitacion>();


	public ConsultaReservaBean () {}

	public String consultaDisponibilidad() {
	    System.out.println("localidad: " + localidad);
	    System.out.println("nomHotel: " + nomHotel);
		
		ArrayList<Hotel> hotelesDevueltos = consultaReserva.consultaDisponibilidad(nomHotel, localidad);
		if (hotelesDevueltos == null) {
			return null;
		}
		for (Hotel h : hotelesDevueltos) {
			listaHoteles.add(h);
		}
		return "listaDeHoteles.xhtml";

	}

	public String consultaDisponibilidadHotel() {
		HashSet<ReservaTipoHabitacion> tipoHabsDevueltas = consultaReserva.consultaDisponibilidadHotel(h, fechaIni, fechaFin);
		if (tipoHabsDevueltas == null) {
			return null;
		}
		for (ReservaTipoHabitacion th : tipoHabsDevueltas) {
			listaTiposHabs.add(th);
		}
		return "disponibilidadDeHabitaciones.xhtml";
	}
	
	public IConsultaReservaRemote getConsultaReserva() {
		return consultaReserva;
	}

	public void setConsultaReserva(IConsultaReservaRemote consultaReserva) {
		this.consultaReserva = consultaReserva;
	}

	public String getNomHotel() {
		return nomHotel;
	}

	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<Hotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<Hotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}

	public Hotel getH() {
		return h;
	}

	public void setH(Hotel h) {
		this.h = h;
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

	public HashSet<ReservaTipoHabitacion> getListaTiposHabs() {
		return listaTiposHabs;
	}

	public void setListaTiposHabs(HashSet<ReservaTipoHabitacion> listaTiposHabs) {
		this.listaTiposHabs = listaTiposHabs;
	}

}
