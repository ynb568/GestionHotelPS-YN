package es.unican.ps.gestionHotel.businessLayer;

@SuppressWarnings("serial")
public class OperacionNoValida extends RuntimeException {

	public OperacionNoValida(String string) {
		super(string);
	}

}
