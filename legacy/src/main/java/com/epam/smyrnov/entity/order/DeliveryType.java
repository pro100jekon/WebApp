package com.epam.smyrnov.entity.order;

public enum DeliveryType {
	NOVA_POSHTA("NovaPoshta"), UKR_POSHTA("UkrPoshta"),
	MEEST_EXPRESS("MeestExpress"), JUST_IN("JustIn"),
	COURIER_NOVA_POSHTA("Courier NovaPoshta"), COURIER_MEEST_EXPRESS("Courier MeestExpress");

	private String value;

	private DeliveryType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
