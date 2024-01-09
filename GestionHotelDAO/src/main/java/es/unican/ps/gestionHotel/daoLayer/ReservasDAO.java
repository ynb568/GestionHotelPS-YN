package es.unican.ps.gestionHotel.daoLayer;

import java.util.ArrayList;

import es.unican.ps.gestionHotel.domain.Reserva;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ReservasDAO implements IReservasDAO {
	
	@PersistenceContext(unitName="gestionHotelPU")
	private EntityManager em;
	
	public Reserva getReserva(int idReserva) {
		return em.find(Reserva.class, idReserva);
	}

	public ArrayList<Reserva> getReservas() {
        return new ArrayList<Reserva>(em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList());
	}

	public boolean anhadeReserva(Reserva r) {
		// Hacemos persistente la reserva
		try {
		em.persist(r);
		return true;
		} catch (EntityExistsException e) {
		// Ya existe una reserva en la BBDD con el mismo id (PK)
		return false;
		}
	}

	public boolean eliminaReserva(Reserva r) {
		// Eliminamos la reserva
		try {
			em.remove(r);
			return true;
		} catch (EntityExistsException e) {
			// No existe una reserva en la BBDD con el mismo id (PK)
			return false;
		}	
	}
	
	public boolean modificaReserva(Reserva nueva) {
		// Modificamos la reserva
		try {
			em.persist(nueva);
			return true;
		} catch (EntityExistsException e) {
			// No existe una reserva en la BBDD con el mismo id (PK)
			return false;
		}
	}

}
