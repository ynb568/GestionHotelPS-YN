package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.*;

public interface IConsultaReserva {
	
	public ArrayList<Hotel> consultaDisponibilidad(String nomHotel, String localidad);
	
	public ArrayList<TipoHabitacion> consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin);
	
	public Reserva consultaReserva(int idReserva);
	
	public ArrayList<Reserva> consultaReservas(LocalDate fechaEntrada, LocalDate fechaSalida);

}
