package com.practice.store.user.service;

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
        ExceptionUtil.isException400(!VadliationUtil.isEnglishAndKorean(name), "한글 또는 영문으로 입력해 주세요.");
    }

    public void checkMobile(String mobile)throws Exception {
        ExceptionUtil.isException400(!StringUtils.hasText(mobile), "휴대폰 번호를 입력해 주세요.");
        ExceptionUtil.isException400(mobile.charAt(0) != '0', "휴대폰 번호를 확인해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isShorterLength(mobile, 9), "휴대폰 번호를 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isLongerLength(mobile, 11), "휴대폰 번호를 11자 이하로 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isNumber(mobile), "휴대폰 번호는 숫자만 입력해 주세요.");
    }

    public void checkPassword(String password) {
        ExceptionUtil.isException400(!StringUtils.hasText(password), "비밀번호를 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isShorterLength(password, 8), "8자 이상으로 입력해 주세요.");
        ExceptionUtil.isException400(VadliationUtil.isLongerLength(password, 16), "16자 이하로 입력해 주세요.");
    }

    public void checkEmail(String email) {
        ExceptionUtil.isException400(!StringUtils.hasText(email), "이메일을 입력해 주세요.");
        ExceptionUtil.isException400(!VadliationUtil.isEmail(email), "이메일 형식을 확인해주세요.");

        UserEntity userEntity = userIRepository.findByEmail(email).orElse(null);
        ExceptionUtil.isException400(Objects.nonNull(userEntity), "이미 존재하는 아이디(email)입니다.");
    }
}
