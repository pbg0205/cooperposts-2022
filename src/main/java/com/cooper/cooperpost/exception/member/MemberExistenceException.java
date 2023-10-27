package com.cooper.cooperpost.exception.member;

import com.cooper.cooperpost.support.error.ErrorCode;

public class MemberExistenceException extends MemberException {

	public MemberExistenceException(ErrorCode errorCode, Object data) {
		super(errorCode, data);
	}
}
