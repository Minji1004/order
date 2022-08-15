package com.practice.store.user.model.request;

import com.practice.store.user.type.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
	private String name;
	private String nickname;
    private String password;
    private String email;
    private String mobile;
    private Sex sex;
}
