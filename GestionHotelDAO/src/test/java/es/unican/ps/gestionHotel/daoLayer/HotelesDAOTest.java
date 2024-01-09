package es.unican.ps.gestionHotel.daoLayer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.unican.ps.gestionHotel.domain.Hotel;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
/*
public class HotelesDAOTest {
	@Mock
	private IHotelesDAO mockHotelesDAO;
	@Mock
	private EntityManager em;

	private HotelesDAO hotelesDAO; // Asegúrate de que esta es la clase correcta.

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		//hotelesDAO = new HotelesDAO(em); // La clase HotelesDAO debe tomar un EntityManager como argumento.
	}

	@Test
	void testAnhadeHotelExitoso() {
		// Arrange
		Hotel h1 = new Hotel("Hotel Sardinero", "Paseo Pereda 15", "Santander", null, null);

		// Act
		doNothing().when(em).persist(h1);
		boolean resultado = false;
		try {
			resultado = hotelesDAO.anhadeHotel(h1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Assert
		assertTrue(resultado, "Se esperaba la adición exitosa del hotel");
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
			assertFalse(resultado, "Se esperaba que la adición del hotel fallara debido a la existencia previa");
		} catch (Exception e) {
			e.printStackTrace();
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
			assertFalse(resultado, "Se esperaba que la adición del hotel fallara debido a una excepción de persistencia");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/
