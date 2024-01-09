package es.unican.ps.gestionHotel.businessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import org.mockito.*;

import es.unican.ps.gestionHotel.daoLayer.IHotelesDAO;
import es.unican.ps.gestionHotel.daoLayer.IReservasDAO;
import es.unican.ps.gestionHotel.domain.Hotel;

public class ConsultaReservaTest {
	@Mock
	IHotelesDAO mockHoteles;
	@Mock
	IReservasDAO mockReservas;

	ConsultaReserva sut;

	@BeforeEach
	public void inicializa() {
		MockitoAnnotations.openMocks(this); //Creacion de los mocks definidos
		sut = new ConsultaReserva(mockHoteles, mockReservas); //Uso de los mocks para la creacion de la clase de prueba		
	}

	// Test cases for consultaDisponibilidad(String nomHotel, String localidad)
	@Test
	public void UCD_1a_testConsultaDisponibilidadPorNombre() {
		String nomHotel = "6 Flags Santander";
		Hotel hotel = new Hotel(nomHotel, null, null, null, null);
		when(mockHoteles.getHotel(nomHotel)).thenReturn(hotel);

		ArrayList<Hotel> resultado = sut.consultaDisponibilidad(nomHotel, null);

		assertEquals(Arrays.asList(hotel), resultado);
	}
 
	@Test
	public void UCD_1b_testConsultaDisponibilidadPorLocalidad() {
		String localidad = "Santander";
		ArrayList<Hotel> hotelesList = new ArrayList<Hotel>();
		
		Hotel h1 = new Hotel("Hotel 1", null, "Santander", null, null);
		Hotel h2 = new Hotel("Hotel 2", null, "Santander", null, null);
		hotelesList.add(h1);
		hotelesList.add(h2);
		when(mockHoteles.getHotelesLocalidad(localidad)).thenReturn(hotelesList);

		ArrayList<Hotel> resultado = sut.consultaDisponibilidad(null, localidad);

		assertEquals(hotelesList, resultado);
	}


	@Test
	public void UCD_1c_testConsultaDisponibilidadNombreYLocalidad() {
		assertThrows(OperacionNoValida.class, () -> sut.consultaDisponibilidad("Milton Santander", "Santander"));
	}


	@Test
	public void UCD1_d_testConsultaDisponibilidadHotelNoExistente() {
		String nomHotel = "Hotel Manolo";
		when(mockHoteles.getHotel(nomHotel)).thenReturn(null);

		ArrayList<Hotel> resultado = sut.consultaDisponibilidad(nomHotel, null);

		assertNull(resultado);
	}

	@Test
	public void UCD1_e_testConsultaDisponibilidadLocalidadNoExistente() {
		String localidad = "Budapest";
		when(mockHoteles.getHotelesLocalidad(localidad)).thenReturn(new ArrayList<Hotel>());

		ArrayList<Hotel> resultado = sut.consultaDisponibilidad(null, localidad);

		assertNull(resultado);
	}
	
	@Test
	public void UCD_1f_testConsultaDisponibilidadNoNombreNoLocalidad() {
		assertThrows(OperacionNoValida.class, () -> sut.consultaDisponibilidad(null, null));
	}

	/*
    // Test cases for consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin)
    //ELEGIR UNO PARA UGIC2A
    @Test
    public void UGIC_2a_testConsultaDisponibilidadHotelConReservas1() {
        // Datos de configuración del test
        Hotel hotel = new Hotel("Hotel Milton", null, null, null, null);
        LocalDate fechaIni = LocalDate.of(2024, 11, 12);
        LocalDate fechaFin = LocalDate.of(2024, 11, 15);
        ArrayList<TipoHabitacion> habitacionesEsperadas = new ArrayList<>();
        habitacionesEsperadas.add(new TipoHabitacion("Doble", null, 2));
        habitacionesEsperadas.add(new TipoHabitacion("Individual", null, 1));

        // Definición de comportamiento esperado del mock
        when(mockHoteles.consultaDisponibilidadHotel(hotel, fechaIni, fechaFin)).thenReturn(habitacionesEsperadas);

        // Ejecución del método bajo prueba
        ArrayList<TipoHabitacion> resultado = sut.consultaDisponibilidadHotel(hotel, fechaIni, fechaFin);

        // Verificación de que el resultado es el esperado
        assertEquals(habitacionesEsperadas, resultado);
    }


    //Test cases for consultaDisponibilidadHotel(Hotel h, LocalDate fechaIni, LocalDate fechaFin)
    @Test
    public void UGIC_2a_testConsultaDisponibilidadHotelConReservas2() {
     LocalDate fechaIni = LocalDate.of(2023, 5, 1); 
     LocalDate fechaFin = LocalDate.of(2023, 5, 10);
     Hotel hotel = new Hotel("6 Flags Santander", null, null, null, null); // Properly configure the behaviors of the Hotel object
     ArrayList<Reserva> reservas = new ArrayList<Reserva>(); // properly configure the list of reservations
     when(mockHoteles.anhadeHotel("6 Flags Santander", null, null, null, null)).thenReturn(true);
     when(mockHoteles.getHotel("6 Flags Santander").getReservas()).thenReturn(reservas);

     ArrayList<TipoHabitacion> habitaciones = new ArrayList<TipoHabitacion>(); // properly configure the list of room types
     when(mockHoteles.getHotel("6 Flags Santander").getHabitaciones()).thenReturn(habitaciones);

     ArrayList<TipoHabitacion> resultado = sut.consultaDisponibilidadHotel(hotel, fechaIni, fechaFin);

     // Assert the returned arrayList's content matches your expectations
    }

    @Test
    public void UGIC_2b_testConsultaDisponibilidadHotelSinReservas() {
     LocalDate fechaIni = LocalDate.of(2023, 5, 1); 
     LocalDate fechaFin = LocalDate.of(2023, 5, 10);
     Hotel hotel = new Hotel("6 Flags Santander", null, null, null, null); // Properly configure the behaviors of the Hotel object

     when(mockHoteles.getHotel("6 Flags Santander").getReservas()).thenReturn(new ArrayList<Reserva>()); // No reservations
     when(mockHoteles.getHotel("6 Flags Santander").getHabitaciones()).thenReturn(new ArrayList<TipoHabitacion>()); // No room types

     ArrayList<TipoHabitacion> resultado = sut.consultaDisponibilidadHotel(hotel, fechaIni, fechaFin);

     // Assert the returned arrayList's content matches your expectations

    }

    // Test cases for consultaReserva(int idReserva)
    @Test
    public void UGIC_3a_testConsultaReservaExistente() {
        // Setup reservation with id 2
        Reserva reserva = new Reserva(null, null, 0, null, null, null);
        reserva.setId(2);
        // Assume method setup: reserva.setId(2), etc.
        when(mockReservas.getReserva(2)).thenReturn(reserva);

        Reserva result = sut.consultaReserva(2);

        // Validate that the reservation returned has the same ID as the one requested
        assertEquals(reserva, result);
    }

    @Test
    public void UGIC_3b_testConsultaReservaNoExistente() {
        int reservaId = 12345;
        when(mockReservas.getReserva(reservaId)).thenReturn(null);

        Reserva resultado = sut.consultaReserva(reservaId);

        assertNull(resultado);
    }

    // Test cases for consultaReservas(LocalDate fechaEntrada, LocalDate fechaSalida)
    // UGIC.4a and UGIC.4b should be added here, remembering to return the correct list of reservations or `null`.




  //Test cases for consultaReservas(LocalDate fechaEntrada, LocalDate fechaSalida)
  @Test
  public void UGIC_4a_testConsultaReservasConReservas() {
   LocalDate fechaEntrada = LocalDate.of(2023, 5, 1); 
   LocalDate fechaSalida = LocalDate.of(2023, 5, 10);

   ArrayList<Reserva> reservasList = new ArrayList<Reserva>(); // configure the list of reservations
   when(mockReservas.getReservas()).thenReturn(reservasList);

   ArrayList<Reserva> resultado = sut.consultaReservas(fechaEntrada, fechaSalida);

   // Assert the returned arrayList's content matches your expectations
  }

  @Test
  public void UGIC_4b_testConsultaReservasSinReservas() {
   LocalDate fechaEntrada = LocalDate.of(2023, 5, 1); 
   LocalDate fechaSalida = LocalDate.of(2023, 5, 10);
   when(mockReservas.getReservas()).thenReturn(new ArrayList<Reserva>()); // No reservations

   ArrayList<Reserva> resultado = sut.consultaReservas(fechaEntrada, fechaSalida);

   // Assert the returned arrayList's content matches your expectations
  }

  */

}


