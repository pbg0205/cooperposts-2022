package com.cooper.cooperpost.support.response;

import lombok.Getter;

import com.cooper.cooperpost.support.error.ErrorCode;
import com.cooper.cooperpost.support.error.ErrorMessage;

@Getter
public class ApiResponse<S> {

	private final ResultType result;

	private final S data;

	private final ErrorMessage error;

	private ApiResponse(ResultType result, S data, ErrorMessage error) {
		this.result = result;
		this.data = data;
		this.error = error;
	}

	public static ApiResponse<?> success() {
		return new ApiResponse<>(ResultType.SUCCESS, null, null);
	}

	public static <S> ApiResponse<S> success(S data) {
		return new ApiResponse<>(ResultType.SUCCESS, data, null);
	}

	public static ApiResponse<?> error(ErrorCode errorCode, String message) {
		return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(errorCode, message));
	}

	public static ApiResponse<?> error(ErrorCode errorCode, String message, Object errorData) {
		return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(errorCode, message, errorData));
	}


}
