package es.unican.ps.gestionHotel.daoLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.*;

public interface IHotelesDAO {
	
	public Hotel getHotel(String nomHotel);
		
	public ArrayList<Hotel> getHoteles();
	
	public ArrayList<Hotel> getHotelesLocalidad(String localidad);
	
	public boolean anhadeHotel(Hotel h);
	
	public boolean eliminaHotel(Hotel h);
	
	public boolean modificaHotel(Hotel nuevo); 
}
