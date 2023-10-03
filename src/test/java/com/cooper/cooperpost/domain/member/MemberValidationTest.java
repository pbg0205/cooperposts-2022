package com.cooper.cooperpost.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("사용자 검증 테스트")
class MemberValidationTest {

	private static Validator validator;

	@BeforeAll
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory()
			.getValidator();
	}

	@ParameterizedTest(name = "#{index} - 패스워드 입력 값 : {0}")
	@MethodSource("validPasswordProvider")
	@DisplayName("비밀번호는 영문 대소문자, 숫자, 특수문자를 포함하고, 최소 8자, 최대 20자이다.")
	void testPasswordRegexValid(String password) {
		Member member = Member.create("사용자1", "cooper@gmail.com", password);

		Set<ConstraintViolation<Member>> violations = validator.validate(member);
		assertThat(violations.size()).isZero();
	}

	private static Stream<String> validPasswordProvider() {
		return Stream.of(
			"AAAbbbccc@123",
			"Hello world$123",
			"A!@#&()–a1",               // test punctuation part 1
			"A[{}]:;',?/*a1",           // test punctuation part 2
			"A~$^+=<>a1",               // test symbols
			"0123456789$abcdefgAB",     // test 20 chars
			"123Aa$Aa"
		);
	}

	@ParameterizedTest(name = "#{index} - 이메일 입력 값 : {0}")
	@MethodSource("validEmailProvider")
	@DisplayName("이메일은 아이디가 64자 이내만 가능하고, 하이픈(-), 점(.)만 포함한다.")
	void testEmailRegexValid(String email) {
		Member member = Member.create("사용자1", email, "AAAbbbccc@123");

		Set<ConstraintViolation<Member>> violations = validator.validate(member);
		assertThat(violations.size()).isZero();
	}

	private static Stream<String> validEmailProvider() {
		return Stream.of(
			"username@domain.com",
			"user.name@domain.com",
			"user-name@domain.com",
			"username@domain.co.in",
			"user_name@domain.com"
		);
	}
}
