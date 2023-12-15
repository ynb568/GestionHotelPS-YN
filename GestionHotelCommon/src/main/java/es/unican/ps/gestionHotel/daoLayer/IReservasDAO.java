package es.unican.ps.gestionHotel.daoLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.*;

public interface IReservasDAO {
	
	public Reserva getReserva(int idReserva);
	
	public ArrayList<Reserva> getReservas();
	
	public boolean anhadeReserva(Reserva r);
	
	public boolean eliminaReserva(Reserva r);
	
	public boolean modificaReserva(Reserva nueva);
}
