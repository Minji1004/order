package com.practice.store.user.service.external;

import com.practice.store.config.model.response.CommonListResponse;
import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.order.service.internal.InternalOrderService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalUserService {

	private final SignUpValidationService signUpValidationService;
	private final InternalUserService internalUserService;
	private final InternalOrderService internalOrderService;

	private final UserIRepository userIRepoistory;
	private final UserQRepository userQRepository;

	/*
	회원 가입
	 */
	@Transactional
	public void signUp(SignUpRequest signUpRequest) throws Exception {

		signUpValidationService.checkName(signUpRequest.getName());
		signUpValidationService.checkNickName(signUpRequest.getNickname());
		signUpValidationService.checkPassword(signUpRequest.getPassword());
		signUpValidationService.checkMobile(signUpRequest.getMobile());
		signUpValidationService.checkEmail(signUpRequest.getEmail());

		userIRepoistory.save(UserEntity.of(signUpRequest));
	}

	/*
	회원 목록 조회
	 */
	@Transactional(readOnly = true)
    public CommonListResponse getUserList(String name, String email, Integer currentPage, Integer pageSize) {
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		List<User> userList = userQRepository.findByNameAndEmail(name, email, pageable);
		int totalCount = userQRepository.getTotalCountByNameNadEmail(name, email);

		List<Long> userIdList = userList.stream().map(User::getUserId).collect(Collectors.toList());
		Map<Long, OrderResponse> lastOrderMap = internalOrderService.getLastOrderMap(userIdList);

		List<UserListResponse> userResponseList =
				userList
						.stream()
						.map(user -> {
							OrderResponse orderResponse = lastOrderMap.get(user.getUserId());
							return new UserListResponse(user, orderResponse);
						})
						.collect(Collectors.toList());

		return new CommonListResponse(new PageImpl<UserListResponse>(userResponseList, pageable, totalCount));
    }

    /*
    회원 상세 조회
     */
	@Transactional(readOnly = true)
	public UserDetailResponse getUser(String email, Long userId) {
		User user = new User(internalUserService.getUserEntity(email, userId));
		return new UserDetailResponse(user);
	}
}
