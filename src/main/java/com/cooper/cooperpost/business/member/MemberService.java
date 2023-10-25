package com.cooper.cooperpost.business.member;

import com.cooper.cooperpost.dto.member.MemberCreateRequest;
import com.cooper.cooperpost.dto.member.MemberCreateResponse;

public interface MemberService {

	MemberCreateResponse createMember(MemberCreateRequest memberCreateRequest);

}
