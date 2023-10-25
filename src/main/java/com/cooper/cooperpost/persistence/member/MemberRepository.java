package com.cooper.cooperpost.persistence.member;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooper.cooperpost.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {

}
