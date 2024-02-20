package es.unican.ps.GestionHotel.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.businessLayer.IGestionReservaRemote;
import es.unican.ps.gestionHotel.domain.DatosCliente;
import es.unican.ps.gestionHotel.domain.DatosPago;
import es.unican.ps.gestionHotel.domain.ReservaTipoHabitacion;
import es.unican.ps.gestionHotel.domain.TipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class GestionReservaBean implements Serializable {

	
	@EJB
	private IGestionReservaRemote gestionReserva;
	@Inject
    private ConsultaReservaBean consultaReservaBean;
	
	private String nombre;
	private String dni;
	private DatosCliente dc;
	
	private String numTrajeta;
	private DatosPago dp;
	
	private int numHabitacionesSelec;
	private ArrayList<ReservaTipoHabitacion> tiposSeleccionados;
	private ArrayList<ReservaTipoHabitacion> tipos;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private String nombreHotel;
	private int numeroReserva;
	
	public GestionReservaBean () {}
	
	public String preparaHabitacionesReserva() {
        if (numHabitacionesSelec > 0) {
            for (ReservaTipoHabitacion habitacion : tipos) {
                habitacion.setNumHabitaciones(numHabitacionesSelec);
                tiposSeleccionados.add(habitacion);
            }
        }
		return "datosReserva.xhtml";
	}
	
	public String creaReserva() {
		dc.setNombre(nombre);
		dc.setDni(dni);
		dp.setNumTarjeta(numTrajeta);
		fechaIni = consultaReservaBean.getFechaIni();
		fechaFin = consultaReservaBean.getFechaFin();
		nombreHotel = consultaReservaBean.getNomHotel();
		
		int numeroReservaDevuelto = gestionReserva.creaReserva(dc, dp, tiposSeleccionados, fechaIni, fechaFin, nombreHotel);
		if (numeroReservaDevuelto  == -1) {
			return null;
		}
		numeroReserva = numeroReservaDevuelto;
		
		return "confirmacionReserva.xhtml";
	}
	
    public ConsultaReservaBean getConsultaReservaBean() {
        return consultaReservaBean;
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
