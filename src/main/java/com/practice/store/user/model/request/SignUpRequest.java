package com.practice.store.user.model.request;

import com.practice.store.user.type.Sex;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @ApiModelProperty(value = "이름", example = "홍길동")
	private String name;

    @ApiModelProperty(value = "별명" , example = "길동이")
	private String nickname;

    @ApiModelProperty(value = "비밀번호", example = "123!@#abcABC")
    private String password;

    @ApiModelProperty(value = "이메일", example = "abc@def.com")
    private String email;

    @ApiModelProperty(value = "전화번호", example = "01011113333")
    private String mobile;

    @ApiModelProperty(value = "성별", example = "MALE")
    private Sex sex;
}
