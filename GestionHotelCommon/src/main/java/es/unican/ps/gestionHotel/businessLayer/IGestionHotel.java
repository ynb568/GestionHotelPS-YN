package es.unican.ps.gestionHotel.businessLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.*;

public interface IGestionHotel {
	
	public ArrayList<TipoHabitacion> consultaHotel(Hotel h);
	
	public void modificaPrecio(ArrayList<TipoHabitacion> tipoHabs);
	
	public void anhadeTipoHabitacion(TipoHabitacion th);

}
