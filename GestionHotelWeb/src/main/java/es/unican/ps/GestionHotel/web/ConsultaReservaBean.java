package es.unican.ps.GestionHotel.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.businessLayer.IConsultaReservaRemote;
import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.TipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ConsultaReservaBean implements Serializable {
	
	@EJB
	private IConsultaReservaRemote consultaReserva;

	private String nomHotel;
	private String localidad;
	private ArrayList<Hotel> listaHoteles = new ArrayList<Hotel>();
	
	private Hotel h;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private ArrayList<TipoHabitacion> listaTiposHabs = new ArrayList<TipoHabitacion>();
	
	
	public ConsultaReservaBean () {}
	
	public String consultaDisponibilidad() {
		ArrayList<Hotel> hotelesDevueltos = consultaReserva.consultaDisponibilidad(nomHotel, localidad);
		if (hotelesDevueltos == null) {
			return null;
		}
		for (Hotel h : hotelesDevueltos) {
			listaHoteles.add(h);
		}
		return "consulta.xhtml";
		
	}
	
	public String consultaDisponibilidadHotel() {
		ArrayList<TipoHabitacion> tipoHabsDevueltas = consultaReserva.consultaDisponibilidadHotel(h, fechaIni, fechaFin);
		if (tipoHabsDevueltas == null) {
			return null;
		}
		for (TipoHabitacion th : tipoHabsDevueltas) {
			listaTiposHabs.add(th);
		}
		return "consulta.xhtml";
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

	public ArrayList<TipoHabitacion> getListaTiposHabs() {
		return listaTiposHabs;
	}

	public void setListaTiposHabs(ArrayList<TipoHabitacion> listaTiposHabs) {
		this.listaTiposHabs = listaTiposHabs;
	}

}
