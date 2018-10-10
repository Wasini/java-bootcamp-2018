package com.globant.bootcamp.services.transaction;

import lombok.Getter;

@Getter
public class PaymentException extends Exception {

	private final PaymentErrorCode errorCode;

	public PaymentException(PaymentErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public PaymentException(String message, Throwable cause, PaymentErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public PaymentException(String message, PaymentErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public PaymentException(Throwable cause, PaymentErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
}
