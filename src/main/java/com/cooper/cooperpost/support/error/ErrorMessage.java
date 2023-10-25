package com.cooper.cooperpost.support.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

	private final String code;

	private final String message;

	private final Object data;

	public ErrorMessage(ErrorCode code, String message) {
		this.code = code.name();
		this.message = message;
		this.data = null;
	}

	public ErrorMessage(ErrorCode code, String message, Object data) {
		this.code = code.name();
		this.message = message;
		this.data = data;
	}
}
