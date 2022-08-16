package com.practice.store.user.controller;

import com.practice.store.auth.annotation.CheckLogin;
import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.type.AccessTokenParamType;
import com.practice.store.config.model.response.CommonListResponse;
import com.practice.store.user.model.response.UserDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.user.service.external.ExternalUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/user")
public class UserController {
	private final ExternalUserService userService;

	/*
	회원 가입
	 */
	@PostMapping("/sign-up")
	public ResponseEntity<Void> sign(@RequestBody SignUpRequest signUpRequest) throws Exception {
		userService.signUp(signUpRequest);
		return ResponseEntity.ok().build();
	}

	/*
	여러 회원 목록 조회
	 */
	@CheckLogin
	@GetMapping
	public ResponseEntity<CommonListResponse> getUserList(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "currentPage", required = true) Integer currentPage,
			@RequestParam(name = "pageSize", required = true) Integer pageSize
	) throws Exception {
		return ResponseEntity.ok().body(userService.getUserList(name, email, currentPage, pageSize));
	}

	/*
	단일 회원 상세 정보
	 */
	@CheckLogin
	@GetMapping("/detail/{userId}")
	public ResponseEntity<UserDetailResponse> getUser(
			@ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
			@PathVariable("userId") Long userId) throws Exception {
		return ResponseEntity.ok().body(userService.getUser(email, userId));
	}
}
