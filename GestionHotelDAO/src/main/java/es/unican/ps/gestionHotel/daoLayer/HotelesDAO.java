package es.unican.ps.gestionHotel.daoLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.Hotel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Stateless
public class HotelesDAO implements IHotelesDAO {
	@PersistenceContext(unitName="gestionHotelPU")
	private EntityManager em;

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
			return false;
		} catch (PersistenceException e) {
			return false;
		}	
	}

	public boolean eliminaHotel(Hotel h) {
		try {
			em.remove(h);
			return true;
		} catch (EntityExistsException e) {
			return false;
		} catch (PersistenceException e) {
			return false;
		}	
	}

	public boolean modificaHotel(Hotel nuevo) {
		try {
			em.persist(nuevo);
			return true;
		} catch (EntityExistsException e) {
			return false;
		} catch (PersistenceException e) {
			return false;
		}	
	}
}
