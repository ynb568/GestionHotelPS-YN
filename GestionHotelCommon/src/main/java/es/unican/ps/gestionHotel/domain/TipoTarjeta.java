package es.unican.ps.gestionHotel.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum TipoTarjeta {
	MASTERCARD,
	VISA,
	AMERICAN_EXPRESS
}

