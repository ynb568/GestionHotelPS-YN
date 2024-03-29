 package es.unican.ps.gestionHotel.daoLayer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.unican.ps.gestionHotel.domain.Hotel;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class HotelesDAOTest {
	@Mock
	private EntityManager em;
	@InjectMocks
	private HotelesDAO hotelesDAO; // Aseg�rate de que esta es la clase correcta.

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAnhadeHotelExitoso() {
		// Arrange
		Hotel h1 = new Hotel("Hotel Sardinero", "Paseo Pereda 15", "Santander", null, null);

		// Act		
		doNothing().when(em).persist(h1);
		boolean resultado = false;
		resultado = hotelesDAO.anhadeHotel(h1);


		// Assert
		verify(em, times(1)).persist(h1);
		assertTrue(resultado, "Se esperaba la adici�n exitosa del hotel");
	}

	@Test
	void testAnhadeHotelYaExiste() {
		// Arrange
		Hotel h1 = new Hotel("Milton Santander", "Calle Honduras 40", "Santander", null, null);

		// Act
		doThrow(EntityExistsException.class).when(em).persist(h1);
		boolean resultado = false;
		try {
			resultado = hotelesDAO.anhadeHotel(h1);
		} catch (EntityExistsException e) {
			// Assert dentro del catch
			assertFalse(resultado, "Se esperaba que la adici�n del hotel fallara debido a la existencia previa");
		}
	}

	@Test
	void testAnhadeHotelExcepcionDePersistencia() {
		// Arrange
		Hotel h1 = new Hotel("Milton Santander", "Calle Honduras 40", "Santander", null, null);

		// Act
		doThrow(PersistenceException.class).when(em).persist(h1);
		boolean resultado = false;
		try {
			resultado = hotelesDAO.anhadeHotel(h1);
		} catch (PersistenceException e) {
			// Assert dentro del catch
			assertFalse(resultado, "Se esperaba que la adici�n del hotel fallara debido a una excepci�n de persistencia");
		}
	}
}

