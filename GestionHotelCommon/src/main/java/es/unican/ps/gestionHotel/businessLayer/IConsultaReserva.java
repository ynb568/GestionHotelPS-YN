package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import es.unican.ps.gestionHotel.domain.*;

public interface IConsultaReserva {
	
	public ArrayList<Hotel> consultaDisponibilidad(String nomHotel, String localidad);
	
	public HashSet<ReservaTipoHabitacion> consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin);
	
	public Reserva consultaReservaPorId(int idReserva);
	
	public ArrayList<Reserva> consultaReservas(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	public HashSet<ReservaTipoHabitacion> convertirASetReservaTipo(ArrayList<TipoHabitacion> tiposHabitacion);

}
