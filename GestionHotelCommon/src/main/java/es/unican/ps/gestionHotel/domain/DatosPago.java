package es.unican.ps.gestionHotel.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tarjetas")
public class DatosPago {
	@Id
	private String numTarjeta;
	private String cvc;
	private String mesCaducidad;
	private String anhoCaducidad;
	@Enumerated(EnumType.STRING)
	@Embedded
	private TipoTarjeta tipo;
	
	public DatosPago() {}
	
	public DatosPago (String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getMesCaducidad() {
		return mesCaducidad;
	}

	public void setMesCaducidad(String mesCaducidad) {
		this.mesCaducidad = mesCaducidad;
	}

	public String getAnhoCaducidad() {
		return anhoCaducidad;
	}

	public void setAnhoCaducidad(String anhoCaducidad) {
		this.anhoCaducidad = anhoCaducidad;
	}

	public TipoTarjeta getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarjeta tipo) {
		this.tipo = tipo;
	}
	
	

}
