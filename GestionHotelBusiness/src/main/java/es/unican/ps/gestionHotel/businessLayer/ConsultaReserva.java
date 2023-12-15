package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.daoLayer.IHotelesDAO;
import es.unican.ps.gestionHotel.daoLayer.IReservasDAO;
import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.Reserva;
import es.unican.ps.gestionHotel.domain.TipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ConsultaReserva implements IConsultaReserva {
	@EJB
	private IHotelesDAO hoteles;
	@EJB
	private IReservasDAO reservas;

	public ConsultaReserva (IHotelesDAO hoteles, IReservasDAO reservas) {
		this.hoteles = hoteles;
		this.reservas = reservas;
	}

	/*
	 * Solo se gestiona en el caso que se busque por nombre o localidad 
	 * 		si se busca por ambos devuelve null
	 */
	public ArrayList<Hotel> consultaDisponibilidad(String nomHotel, String localidad) {
		ArrayList<Hotel> hotelesSelec = new ArrayList<Hotel>();
		if (nomHotel != null && localidad == null) {
			Hotel hotelNombre = hoteles.getHotel(nomHotel);
			if (hotelNombre != null) {
				hotelesSelec.add(hotelNombre);
			} else {
				//No existe el hotel
				return null;
			}

		} else if (nomHotel == null && localidad != null) {
			ArrayList<Hotel> hotelesLoc = hoteles.getHotelesLocalidad(localidad);
			if (hotelesLoc.isEmpty()) {
				//No hay hoteles en la localidad
				return null;
			}
		} else if (nomHotel == null && localidad == null) {
			throw new OperacionNoValida("Filtro de b�squeda no insertado");
		} else {
			throw new OperacionNoValida("�nicamente utilizar un filto de busqueda (Nombre de Hotel � Localidad)");
		}
		return hotelesSelec;
	}

	public ArrayList<TipoHabitacion> consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin) {
		ArrayList<TipoHabitacion> habsDisponibles = new ArrayList<TipoHabitacion>();
		ArrayList<Reserva> reservasHotel = h.getReservas();
		for (TipoHabitacion th : h.getHabitaciones()) {
			boolean isDisponible = true;
			for (Reserva r : reservasHotel) {
				if ((fechaIni.isBefore(r.getFechaEntrada()) && (fechaFin.isAfter(r.getFechaEntrada()) && fechaFin.isBefore(r.getFechaSalida()))) ||
					    (fechaIni.isAfter(r.getFechaEntrada()) && fechaIni.isBefore(r.getFechaSalida()) && fechaFin.isAfter(r.getFechaSalida())) ||
					    (fechaIni.isAfter(r.getFechaEntrada()) && fechaFin.isBefore(r.getFechaSalida())) ||
					    (fechaIni.isBefore(r.getFechaEntrada()) && fechaFin.isAfter(r.getFechaSalida()))) {
					isDisponible = false;
				}
			}	
			if (isDisponible) {
				habsDisponibles.add(th);
			}
		}
		return habsDisponibles;
	}

	public Reserva consultaReserva(int idReserva) {
		return reservas.getReserva(idReserva);
	}

	public ArrayList<Reserva> consultaReservas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		ArrayList<Reserva> reservasFechas = new ArrayList<Reserva>();
		if (fechaEntrada.isAfter(fechaSalida)) {
			throw new OperacionNoValida("Las fechas especificadas son incorrectas");
		}
		for (Reserva r : reservas.getReservas()) {
			//se muestran las reservas con fecha entrada entre rangos o posteriores a la entrada si la salida es nula
			if (r.getFechaEntrada().isAfter(fechaEntrada) && r.getFechaEntrada().isBefore(fechaSalida) || 
					r.getFechaEntrada().isAfter(fechaEntrada) && fechaSalida == null) {
				reservasFechas.add(r);
			}
		}
		return null;
	}
}
