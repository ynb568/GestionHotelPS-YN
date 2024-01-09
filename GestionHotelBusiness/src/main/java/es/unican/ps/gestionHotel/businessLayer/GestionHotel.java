package es.unican.ps.gestionHotel.businessLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.Hotel;
import es.unican.ps.gestionHotel.domain.TipoHabitacion;
import jakarta.ejb.Stateless;

@Stateless
public class GestionHotel implements IGestionHotel, IGestionHotelLocal, IGestionHotelRemote {
	
	IGestionHotel hoteles;
	
	public GestionHotel () { }
	
	public GestionHotel (IGestionHotel hoteles) {
		this.hoteles = hoteles;
	}

	public ArrayList<TipoHabitacion> consultaHotel(Hotel h) {
		ArrayList<TipoHabitacion> tiposHabsHotel = new ArrayList<TipoHabitacion>();
		
		for (TipoHabitacion th : h.getHabitaciones()) {
			tiposHabsHotel.add(th);
		}
		return tiposHabsHotel;
	}

	public void modificaPrecio(ArrayList<TipoHabitacion> tipoHabs) {
		// TODO Auto-generated method stub
		
	}

	public void anhadeTipoHabitacion(TipoHabitacion th) {
		// TODO Auto-generated method stub
		
	}

}
