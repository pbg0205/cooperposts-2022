package com.cooper.cooperpost.exception.member;

import lombok.Getter;

import com.cooper.cooperpost.support.error.ErrorCode;

@Getter
public class MemberException extends RuntimeException {

	private static final String MEMBER_EXCEPTION_DEFAULT_MESSAGE = "member domain exception occurred.";

	private ErrorCode errorCode;
	private Object data;

	public MemberException(ErrorCode errorCode, Object data) {
		super(MEMBER_EXCEPTION_DEFAULT_MESSAGE);
		this.errorCode = errorCode;
		this.data = data;
	}

}
