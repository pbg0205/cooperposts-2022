package com.cooper.cooperpost.acceptance.base;

import static com.cooper.cooperpost.acceptance.base.MemberSteps.회원_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@DisplayName("멤버 인수 테스트")
public class MemberAcceptanceTest extends AcceptanceTestBase {

	@DisplayName("회원 가입 성공")
	@Test
	void createMember() {
		//given, when
		ExtractableResponse<Response> response = 회원_생성_요청("cooper@gmail.com", "Cooper1234!", "cooper");

		//then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@DisplayName("회원 가입 실패")
	@ParameterizedTest(name = "[{index}] {arguments}")
	@MethodSource("invalidMemberCreateRequestProvider")
	void createMemberFailByPassword(String email, String password, String name) {
		//given, when
		ExtractableResponse<Response> response = 회원_생성_요청(email, password, name);

		//then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	private static Stream<Arguments> invalidMemberCreateRequestProvider() {
		return Stream.of(
			Arguments.arguments("cooper@gmail.com", "Cooper!", "cooper"), // invalid password
			Arguments.arguments("cooper@gmail", "Cooper1234!", "cooper"), // invalid email
			Arguments.arguments("cooper@gmail.com", "Cooper1234!", ""), // empty username
			Arguments.arguments("cooper@gmail.com", "Cooper!", "") // invalid password, empty username
		);
	}

	@DisplayName("중복 이메일에 관한 검증")
	@Test
	void validateDuplicateEmail() {
		//given, when
		회원_생성_요청("cooper@gmail.com", "Cooper1234!", "cooper");
		ExtractableResponse<Response> response = 회원_생성_요청("cooper@gmail.com", "Cooper1234!", "cooper");

		//then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}
