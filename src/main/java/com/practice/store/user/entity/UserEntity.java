package com.practice.store.user.entity;

import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.user.type.Sex;
import com.practice.store.user.utils.EncryptUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER_TABLE")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
	private Long userId;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "SEX")
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Builder
	private UserEntity(String name, String nickname, String password, String email, String mobile, Sex sex) {
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.sex = sex;
	}

	public static UserEntity of(SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		String password = EncryptUtil.encryptPassword(signUpRequest.getPassword(), signUpRequest.getEmail());

		return UserEntity.builder()
				.name(signUpRequest.getName())
				.nickname(signUpRequest.getNickname())
				.password(password)
				.email(signUpRequest.getEmail())
				.mobile(signUpRequest.getMobile())
				.sex(signUpRequest.getSex())
				.build();
	}
}
