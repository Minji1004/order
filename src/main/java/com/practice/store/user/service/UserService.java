package com.practice.store.user.service;

import com.practice.store.config.exception.util.ExceptionUtil;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.model.User;
import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.user.model.response.UserListResponse;
import com.practice.store.user.model.response.UserResponse;
import com.practice.store.user.repository.UserIRepository;
import com.practice.store.user.repository.UserQRepository;
import com.practice.store.user.utils.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

	private final SignUpValidationService signUpValidationService;
	private final UserIRepository userIRepoistory;
	private final UserQRepository userQRepository;
	private  final RedisTemplate redisTemplate;

	/*
	회원 가입
	 */
	public void signUp(SignUpRequest signUpRequest) throws Exception {
		signUpValidationService.checkName(signUpRequest.getName());
		signUpValidationService.checkMobile(signUpRequest.getMobile());
		signUpValidationService.checkPassword(signUpRequest.getPassword());
		signUpValidationService.checkEmail(signUpRequest.getEmail());

		userIRepoistory.save(UserEntity.of(signUpRequest));
	}

	/*
	user 로그인 정보 확인
	 */
	public boolean checkUser(String email, String password){
		List<User> userList = userQRepository.findByEmail(email);
		if(userList.size() == 0){
			return false;
		}

		User user = userList.get(0);
		try {

			String encryptPassword = EncryptUtil.encryptPassword(password, email);
			if(encryptPassword.equals(user.getPassword())){
				return true;
			}

		} catch (NoSuchAlgorithmException e) {
			return false;
		}

		return false;
	}

	/*
	회원 목록 조회
	 */
    public UserListResponse getUserList(String name, String email, Integer currentPage, Integer pageSize) {
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		List<User> userList = userQRepository.findByNameAndEmail(name, email, pageable);
		int totalCount = userQRepository.getTotalCountByNameNadEmail(name, email);

		List<UserResponse> userResponseList = userList.stream().map(UserResponse::new).collect(Collectors.toList());
		return new UserListResponse(new PageImpl<UserResponse>(userResponseList, pageable, totalCount));
    }

    /*
    회원 상세 조회
     */
	public UserResponse getUser(String email, Long userId) {
		User user = userQRepository.findByUserId(userId);
		ExceptionUtil.isException404(user==null, "조회하고자 하는 유저 정보가 없습니다.");
		ExceptionUtil.isException403(!user.getEmail().equals(email), "유저 정보 조회 권한이 없습니다.");

		return new UserResponse(user);
	}
}
