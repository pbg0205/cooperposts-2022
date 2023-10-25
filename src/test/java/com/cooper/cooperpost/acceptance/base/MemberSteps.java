package com.cooper.cooperpost.acceptance.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class MemberSteps {

	public static ExtractableResponse<Response> 회원_생성_요청(String email, String password, String name) {
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		params.put("name", name);

		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(params)
			.when().post("/api/v1/members")
			.then().log().all()
			.extract();
	}
}
