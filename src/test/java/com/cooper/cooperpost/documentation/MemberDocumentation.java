package com.cooper.cooperpost.documentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import com.cooper.cooperpost.business.member.MemberService;
import com.cooper.cooperpost.documentation.base.Documentation;
import com.cooper.cooperpost.dto.member.MemberCreateRequest;
import com.cooper.cooperpost.dto.member.MemberCreateResponse;

@DisplayName("회원 API 문서화")
public class MemberDocumentation extends Documentation {

	@Autowired
	private MemberService memberService;

	@DisplayName("회원 생성")
	@Test
	void find_post_list_success() throws Exception {
		MemberCreateRequest memberCreateRequest = new MemberCreateRequest("cooper", "cooper@gmail.com", "Cooper1234!");
		MemberCreateResponse memberCreateResponse = new MemberCreateResponse("cooper@gmail.com");

		given(memberService.createMember(any())).willReturn(memberCreateResponse);

		mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/members")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(memberCreateRequest)))
			.andExpect(status().isOk())
			.andDo(document("create-member", createMemberRequest(), createMemberResponse()));
	}

	private Snippet createMemberRequest() {
		return requestFields(
			fieldWithPath("name").type(JsonFieldType.STRING).description("이름(최소 3자, 최대 30자)"),
			fieldWithPath("email").type(JsonFieldType.STRING).description("사용자 이메일"),
			fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호(최소 8자, 최대 20자, 영대소문자, 숫자, 특수문자 포함")
		);
	}

	private Snippet createMemberResponse() {
		return responseFields(
			fieldWithPath("result").type(JsonFieldType.STRING).description("결과 플래그"),
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("데이터"),
			fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원가입 완료 이메일"),
			fieldWithPath("error").type(JsonFieldType.STRING).description("에러 정보").optional()
		);
	}

}
