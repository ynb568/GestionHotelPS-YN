package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import es.unican.ps.gestionHotel.daoLayer.IHotelesDAO;
import es.unican.ps.gestionHotel.daoLayer.IReservasDAO;
import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.Reserva;
import es.unican.ps.gestionHotel.domain.ReservaTipoHabitacion;
import es.unican.ps.gestionHotel.domain.TipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ConsultaReserva implements IConsultaReserva, IConsultaReservaRemote, IConsultaReservaLocal {
	@EJB
	private IHotelesDAO hoteles;
	@EJB
	private IReservasDAO reservas;
	
	public ConsultaReserva () { }

	public ConsultaReserva (IHotelesDAO hoteles, IReservasDAO reservas) {
		this.hoteles = hoteles;
		this.reservas = reservas;	
	}

	/*
	 * Solo se gestiona en el caso que se busque por nombre o localidad 
	 * 		si se busca por ambos devuelve null
	 */
	public ArrayList<Hotel> consultaDisponibilidad(String nomHotel, String localidad) {
		System.out.println("nombre" + nomHotel + " localidad" + localidad);
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
			hotelesSelec = hoteles.getHotelesLocalidad(localidad);
			if (hotelesSelec.isEmpty()) {
				//No hay hoteles en la localidad
				return null;
			}
		} else if (nomHotel == null && localidad == null) {
			throw new OperacionNoValida("Filtro de búsqueda no insertado");
		} else if (nomHotel != null && localidad != null) {
			throw new OperacionNoValida("Únicamente utilizar un filto de busqueda (Nombre de Hotel ó Localidad)");
		}
		return hotelesSelec;
	}
	
	public HashSet<ReservaTipoHabitacion> consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin) {
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
		HashSet<ReservaTipoHabitacion> tiposParaReserva = convertirASetReservaTipo(habsDisponibles);
		
		return tiposParaReserva;
	}

	public Reserva consultaReservaPorId(int idReserva) {
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
	
    public HashSet<ReservaTipoHabitacion> convertirASetReservaTipo(ArrayList<TipoHabitacion> tiposHabitacion) {
        HashSet<ReservaTipoHabitacion> reservas = new HashSet<>();

        // Número total de tipos
        int numeroTotalTipos = tiposHabitacion.size();

        // Crear instancias de ReservaTipoHabitacion y agregar al HashSet
        for (TipoHabitacion tipo : tiposHabitacion) {
            ReservaTipoHabitacion reserva = new ReservaTipoHabitacion(numeroTotalTipos, tipo);
            reservas.add(reserva);
        }

        return reservas;
    }

}
