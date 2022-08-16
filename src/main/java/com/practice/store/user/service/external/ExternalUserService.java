package com.practice.store.user.service.external;

import com.practice.store.config.model.response.CommonListResponse;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.model.User;
import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.user.model.response.UserDetailResponse;
import com.practice.store.user.model.response.UserListResponse;
import com.practice.store.user.repository.UserIRepository;
import com.practice.store.user.repository.UserQRepository;
import com.practice.store.user.service.internal.InternalUserService;
import com.practice.store.user.service.internal.SignUpValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalUserService {

	private final SignUpValidationService signUpValidationService;
	private final InternalUserService internalUserService;
	private final UserIRepository userIRepoistory;
	private final UserQRepository userQRepository;
	private final RedisTemplate redisTemplate;

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
	회원 목록 조회
	 */
    public CommonListResponse getUserList(String name, String email, Integer currentPage, Integer pageSize) {
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		List<User> userList = userQRepository.findByNameAndEmail(name, email, pageable);
		int totalCount = userQRepository.getTotalCountByNameNadEmail(name, email);

		//Map<Long, OrderResponse> lastOrderMap =

		List<UserListResponse> userResponseList = new ArrayList<>(); //userList.stream().map(UserListResponse::new).collect(Collectors.toList());
		return new CommonListResponse(new PageImpl<UserListResponse>(userResponseList, pageable, totalCount));
    }

    /*
    회원 상세 조회
     */
	public UserDetailResponse getUser(String email, Long userId) {
		User user = new User(internalUserService.getUserEntity(email, userId));
		return new UserDetailResponse(user);
	}
}
