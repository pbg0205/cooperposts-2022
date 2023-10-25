package com.cooper.cooperpost.acceptance.base;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

import com.cooper.cooperpost.acceptance.base.annotation.AcceptanceTest;

@AcceptanceTest
public abstract class AcceptanceTestBase {

	@LocalServerPort
	int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}
}
