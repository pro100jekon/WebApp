package com.epam.smyrnov.entity.order;

public enum PaymentType {
	VISA("Visa"), PAYPAL("PayPal"), MASTERCARD("Mastercard"), WEB_MONEY("WebMoney"), QIWI("QIWI"), CASH("Cash"), ANY_CARD("Any card");

	private String value;

	private PaymentType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
