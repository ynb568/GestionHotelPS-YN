package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import es.unican.ps.gestionHotel.daoLayer.IHotelesDAO;
import es.unican.ps.gestionHotel.daoLayer.IReservasDAO;
import es.unican.ps.gestionHotel.domain.DatosCliente;
import es.unican.ps.gestionHotel.domain.DatosPago;
import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.Reserva;
import es.unican.ps.gestionHotel.domain.ReservaTipoHabitacion;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class GestionReserva implements IGestionReserva {
	
	@EJB
	private IReservasDAO reservas;
	
	@EJB
	private IHotelesDAO hoteles;
	
	public GestionReserva (IReservasDAO reservas, IHotelesDAO hoteles) {
		this.reservas = reservas;
		this.hoteles = hoteles;
	}
	
	

	public Reserva modificaReserva(Reserva rAntigua, Reserva rNueva) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cancelaReserva(Reserva r) {
		// TODO Auto-generated method stub
		
	}
	/*
	//Al acabar este método se cierra la consulta del proceso Reservar de Stateful
	@Remove
	Igual cambiar creaReserva a tipo string para la gestion de errores
	*/
	public int creaReserva(DatosCliente dc, DatosPago dp, ArrayList<ReservaTipoHabitacion> tipos, 
			LocalDate fechaIni, LocalDate fechaFin, String nombreHotel) {
		
		double precioNocheTotal = 0;
		double precioTotal = 0;
		double diasReserva = 0;
		
		//Por cada X numero de tipos de habitaciones se suma al total por noche 
		for (ReservaTipoHabitacion rth : tipos) {
			precioNocheTotal += rth.getTipo().getPrecioPorNoche() * rth.getNumHabitaciones();
		}
		
		//Cálculo de dias que dura la reserva
		diasReserva = ChronoUnit.DAYS.between(fechaIni, fechaFin);
		
		precioTotal = precioNocheTotal * (int)diasReserva;
		
		
		Reserva r = new Reserva(fechaIni, fechaFin, precioTotal, dc, dp, tipos);
		reservas.anhadeReserva(r);
		
		Hotel h = hoteles.getHotel(nombreHotel);
		h.getReservas().add(r);
		hoteles.modificaHotel(h);
		return r.getId();
	}
	
	/*
	 * El remove tambien se puede hacer con un método vacío de cierre de consulta que lo único
	 * que hace es acabar con la transaccion con el stateful, en lugar de hacerlo en el método creaReserva
	 * 
	 * @Remove
	 * public void confirmaReserva() {}
	 */

}
