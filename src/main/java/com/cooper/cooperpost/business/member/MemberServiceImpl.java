package com.cooper.cooperpost.business.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.cooper.cooperpost.domain.member.Member;
import com.cooper.cooperpost.dto.member.MemberCreateRequest;
import com.cooper.cooperpost.dto.member.MemberCreateResponse;
import com.cooper.cooperpost.persistence.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberCreateResponse createMember(MemberCreateRequest memberCreateRequest) {
		String encodedPassword = passwordEncoder.encode(memberCreateRequest.getPassword());

		Member member = Member.create(
			memberCreateRequest.getName(),
			memberCreateRequest.getEmail(),
			encodedPassword
		);

		Member savedMember = memberRepository.save(member);

		return new MemberCreateResponse(savedMember.getEmail());
	}
}
