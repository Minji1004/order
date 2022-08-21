package com.practice.store.order.controller;

import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.type.AccessTokenParamType;
import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.order.service.external.ExternalOrderService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
@Api(tags = {"주문정보 API"})
public class OrderController {

    private final ExternalOrderService orderService;

    /*
    단일 회원의 주문 목록 조회
     */
    @GetMapping("/{userId}")
    @ApiOperation(value = "단일 회원의 주문 목록 조회")
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
                    , message = "로그인한 유저와 userId의 유저와 일치하지 않습니다."
            )
    })
    public ResponseEntity<List<OrderResponse>> getOrderList(
            @ApiIgnore @ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
            @PathVariable("userId") Long userId
          ) throws Exception {
        return ResponseEntity.ok().body(orderService.getOrderList(email, userId));
    }

    /*
    주문하기
     */
    @PostMapping("/{userId}")
    @ApiOperation(value = "주문하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "유저 Id", required = true, dataType = "string", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(
                    code = 404
                    , message = "유저 정보가 없습니다."
            ),
            @ApiResponse(
                    code = 403
                    , message = "로그인한 유저와 userId의 유저와 일치하지 않습니다."
            )
    })
    public ResponseEntity<Void> orderItem(
            @ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
            @PathVariable("userId") Long userId,
            @RequestBody OrderRequest orderRequest
    ) throws Exception {
        orderService.order(email,userId, orderRequest);
        return ResponseEntity.ok().build();
    }

}
