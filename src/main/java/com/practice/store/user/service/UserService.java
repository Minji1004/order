package com.practice.store.user.service;

import com.practice.store.config.exception.util.ExceptionUtil;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.model.User;
import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.config.model.response.CommonListResponse;
import com.practice.store.user.model.response.UserDetailResponse;
import com.practice.store.user.model.response.UserListResponse;
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

	/*
	회원 목록 조회
	 */
    public CommonListResponse getUserList(String name, String email, Integer currentPage, Integer pageSize) {
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		List<User> userList = userQRepository.findByNameAndEmail(name, email, pageable);
		int totalCount = userQRepository.getTotalCountByNameNadEmail(name, email);

		List<UserListResponse> userResponseList = userList.stream().map(UserListResponse::new).collect(Collectors.toList());
		return new CommonListResponse(new PageImpl<UserListResponse>(userResponseList, pageable, totalCount));
    }

    /*
    회원 상세 조회
     */
	public UserDetailResponse getUser(String email, Long userId) {
		User user = new User(getUserEntity(email, userId));
		return new UserDetailResponse(user);
	}

	/*
	UserEntity 조회
	 */
	public UserEntity getUserEntity(String email, Long userId){
		UserEntity userEntity = userIRepoistory.findById(userId).orElse(null);

		ExceptionUtil.isException404(userEntity==null, "조회하고자 하는 유저 정보가 없습니다.");
		ExceptionUtil.isException403(!userEntity.getEmail().equals(email), "유저 정보 조회 권한이 없습니다.");

		return userEntity;
	}
}
