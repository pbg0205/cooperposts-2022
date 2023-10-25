package com.cooper.cooperpost.acceptance.base;

import static com.cooper.cooperpost.acceptance.base.MemberSteps.회원_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
