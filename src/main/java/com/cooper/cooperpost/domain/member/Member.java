package com.cooper.cooperpost.domain.member;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

import com.github.f4b6a3.uuid.UuidCreator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.cooper.cooperpost.validation.member.annotation.ValidEmail;
import com.cooper.cooperpost.validation.member.annotation.ValidPassword;

@Entity
@Table(name = "tb_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 150)
	@ValidEmail
	private String email;

	@Column(nullable = false)
	@ColumnTransformer(
		read = "convert_from(decrypt(decode(password,'hex'),'ENC_KEY','aes'),'utf8')",
		write = "encode(encrypt(convert_to(?,'utf8'),'ENC_KEY','aes'),'hex')"
	)
	@ValidPassword
	private String password;

	private Member(UUID id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public static Member create(String name, String email, String password) {
		return new Member(generateUUIDv7(), name, email, password);
	}

	private static UUID generateUUIDv7() {
		return UuidCreator.getTimeOrderedEpoch();
	}
}
