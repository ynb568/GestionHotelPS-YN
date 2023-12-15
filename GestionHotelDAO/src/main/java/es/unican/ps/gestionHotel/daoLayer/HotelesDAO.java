package es.unican.ps.gestionHotel.daoLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.Hotel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

@Stateless
public class HotelesDAO implements IHotelesDAO {
	//@PersistenceContext(unitName="hotelesPU");
	private EntityManager em;

	private ArrayList<Hotel> hoteles;

	public Hotel getHotel(String nomHotel) {
		return em.find(Hotel.class, nomHotel);

	}

	public ArrayList<Hotel> getHoteles() {
		return new ArrayList<Hotel>(em.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList());	
	}

	public ArrayList<Hotel> getHotelesLocalidad(String localidad) {
		return new ArrayList<Hotel>(em.createQuery("SELECT h FROM Hotel h WHERE h.localidad ='" + localidad + "'", Hotel.class).getResultList());	
	}


	public boolean anhadeHotel(Hotel h) {
		// Hacemos persistente el hotel
		try {
			em.persist(h);
			return true;
		} catch (EntityExistsException e) {
			// Ya existe un hotel en la BBDD con el mismo nombre (PK)
			return false;
		}	
	}

	public boolean eliminaHotel(Hotel h) {
		// Eliminamos el hotel
		try {
			em.remove(h);
			return true;
		} catch (EntityExistsException e) {
			// No existe un hotel en la BBDD con el mismo nombre (PK)
			return false;
		}	
	}

	public boolean modificaHotel(Hotel nuevo) {
		// Modificamos el hotel
		try {
			em.persist(nuevo);
			return true;
		} catch (EntityExistsException e) {
			// No existe un hotel en la BBDD con el mismo nombre (PK)
			return false;
		}	
	}
}
