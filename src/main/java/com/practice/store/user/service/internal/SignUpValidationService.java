package com.practice.store.user.service.internal;

import com.practice.store.config.exception.util.ExceptionUtil;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.repository.UserIRepository;
import com.practice.store.user.utils.VadliationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SignUpValidationService {

    private final UserIRepository userIRepository;

    public void checkName(String name) {
        ExceptionUtil.isException400(!StringUtils.hasText(name), "이름을 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isEnglishAndKorean(name), "이름을 한글 또는 영문으로 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isLongerLength(name, 20), "이름을 20자 이하로 입력해 주세요.");
    }

    public void checkNickName(String nickname) {
        ExceptionUtil.isException400(!StringUtils.hasText(nickname), "닉네임을 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isEnglish(nickname), "닉네임을 영문으로 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isLongerLength(nickname, 30), "닉네임을 30자 이하로 입력해 주세요.");
    }

    public void checkPassword(String password) {
        ExceptionUtil.isException400(!StringUtils.hasText(password), "비밀번호를 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isShorterLength(password, 10), "비밀번호를 10자 이상으로 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isPassword(password), "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다.");
    }

    public void checkMobile(String mobile)throws Exception {
        ExceptionUtil.isException400(!StringUtils.hasText(mobile), "휴대폰 번호를 입력해 주세요.");
        ExceptionUtil.isException400(mobile.charAt(0) != '0', "휴대폰 번호를 확인해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isShorterLength(mobile, 9), "휴대폰 번호를 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isLongerLength(mobile, 20), "휴대폰 번호를 20자 이하로 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isNumber(mobile), "휴대폰 번호는 숫자만 입력해 주세요.");
    }

    public void checkEmail(String email) {
        ExceptionUtil.isException400(!StringUtils.hasText(email), "이메일을 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isEmail(email), "이메일 형식을 확인해주세요.");

        UserEntity userEntity = userIRepository.findByEmail(email).orElse(null);
        ExceptionUtil.isException400(Objects.nonNull(userEntity), "이미 존재하는 아이디(email)입니다.");
    }
}
