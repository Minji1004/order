package com.practice.store.user.service.internal;

import com.practice.store.config.exception.util.ExceptionUtil;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.repository.UserIRepository;
import com.practice.store.user.utils.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class InternalUserService {

    private final UserIRepository userIRepoistory;

    /*
    UserEntity 조회
     */
    public UserEntity getUserEntity(String email, Long userId){
        UserEntity userEntity = userIRepoistory.findById(userId).orElse(null);

        ExceptionUtil.isException404(userEntity==null, "조회하고자 하는 유저 정보가 없습니다.");
        ExceptionUtil.isException403(!userEntity.getEmail().equals(email), "유저 정보 조회 권한이 없습니다.");

        return userEntity;
    }

    /*
user 로그인 정보 확인
 */
    public boolean checkUser(String email, String password){
        UserEntity userEntity = userIRepoistory.findByEmail(email).orElse(null);
        if(userEntity == null){
            return false;
        }

        try {

            String encryptPassword = EncryptUtil.encryptPassword(password, email);
            if(encryptPassword.equals(userEntity.getPassword())){
                return true;
            }

        } catch (NoSuchAlgorithmException e) {
            return false;
        }

        return false;
    }
}
