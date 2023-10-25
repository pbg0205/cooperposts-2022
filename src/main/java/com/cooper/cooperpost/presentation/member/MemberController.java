package com.cooper.cooperpost.presentation.member;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.cooper.cooperpost.business.member.MemberService;
import com.cooper.cooperpost.dto.member.MemberCreateRequest;
import com.cooper.cooperpost.dto.member.MemberCreateResponse;
import com.cooper.cooperpost.support.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/v1/members")
	public ResponseEntity<ApiResponse<MemberCreateResponse>> createMember(
		@Valid @RequestBody MemberCreateRequest memberCreateRequest) {
		MemberCreateResponse memberCreateResponse = memberService.createMember(memberCreateRequest);
		return ResponseEntity.ok(ApiResponse.success(memberCreateResponse));
	}
}
