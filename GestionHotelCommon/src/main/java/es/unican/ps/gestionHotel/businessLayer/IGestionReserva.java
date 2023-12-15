package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.*;


public interface IGestionReserva {
	
	public int creaReserva(DatosCliente dc, DatosPago dp, ArrayList<ReservaTipoHabitacion> tipos, 
			LocalDate fechaIni, LocalDate fechaFin, String nombreHotel);
	
	public Reserva modificaReserva(Reserva rAntigua, Reserva rNueva);
	
	public void cancelaReserva(Reserva r);

}
