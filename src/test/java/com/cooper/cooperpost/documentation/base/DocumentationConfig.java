package com.cooper.cooperpost.documentation.base;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.context.annotation.Bean;

import com.cooper.cooperpost.business.member.MemberService;

@TestConfiguration
public class DocumentationConfig {

	@Bean
	public MemberService memberService() {
		return mock(MemberService.class, MockReset.withSettings(MockReset.BEFORE));
	}

}
