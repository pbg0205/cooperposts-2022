package com.cooper.cooperpost.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.cooper.cooperpost.validation.member.annotation.ValidEmail;
import com.cooper.cooperpost.validation.member.annotation.ValidPassword;
import com.cooper.cooperpost.validation.member.annotation.ValidUserName;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberCreateRequest {

	@ValidUserName
	private String name;

	@ValidEmail
	private String email;

	@ValidPassword
	private String password;

}
