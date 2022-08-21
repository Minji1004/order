package com.practice.store.user.controller;

import com.practice.store.auth.annotation.CheckLogin;
import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.type.AccessTokenParamType;
import com.practice.store.config.model.response.CommonListResponse;
import com.practice.store.user.model.response.UserDetailResponse;
import io.swagger.annotations.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practice.store.user.model.request.SignUpRequest;
import com.practice.store.user.service.external.ExternalUserService;

import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Api(tags = {"회원정보 API"})
public class UserController {
	private final ExternalUserService userService;

	/*
	회원 가입
	 */
	@PostMapping("/sign-up")
	@ApiOperation(value = "회원가입")
	@ApiResponses({
			@ApiResponse(
					code = 200
					, message = "회원가입 성공"
			),
			@ApiResponse(
					code = 400
					, message = "회원가입 정보 유효성 검증 실패"
			)
	})
	public ResponseEntity<Void> sign(@RequestBody SignUpRequest signUpRequest) throws Exception {
		userService.signUp(signUpRequest);
		return ResponseEntity.ok().build();
	}

	/*
	여러 회원 목록 조회
	 */
	@CheckLogin
	@GetMapping
	@ApiOperation(value = "유저 리스트 조회", notes = "유저 리스트를 조회한다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "이름", required = false, dataType = "string", paramType = "query", defaultValue = ""),
			@ApiImplicitParam(name = "email", value = "이메일", required = false, dataType = "string", paramType = "query", defaultValue = ""),
			@ApiImplicitParam(name = "currentPage", value = "현재 페이지", required = true, dataType = "integer", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "페이지 크기", required = true, dataType = "integer", paramType = "query"),
	})
	@ApiResponses({
			@ApiResponse(
					code = 200
					, message = "유저 리스트 조회"
			)
	})
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
	@ApiOperation(value = "유저 상세 정보 조회", notes = "유저의 상세 정보를 조회한다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "유저 Id", required = true, dataType = "string", paramType = "path")
	})
	@ApiResponses({
			@ApiResponse(
					code = 200
					, message = "유저 리스트 조회"
			),
			@ApiResponse(
					code = 404
					, message = "유저 정보가 없습니다."
			),
			@ApiResponse(
					code = 403
					, message = "유저 조회 권한이 없습니다."
			)
	})
	public ResponseEntity<UserDetailResponse> getUser(
			@ApiIgnore @ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
			@PathVariable("userId") Long userId) throws Exception {
		return ResponseEntity.ok().body(userService.getUser(email, userId));
	}
}
