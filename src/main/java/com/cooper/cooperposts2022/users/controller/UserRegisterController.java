package com.cooper.cooperposts2022.users.controller;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.users.dto.UserRegisterRequestDto;
import com.cooper.cooperposts2022.users.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping
    public ApiResult<Long> createUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        Long registeredUserId = userRegisterService.registerUser(userRegisterRequestDto);
        return ApiResult.success(registeredUserId, HttpStatus.CREATED);
    }

}
