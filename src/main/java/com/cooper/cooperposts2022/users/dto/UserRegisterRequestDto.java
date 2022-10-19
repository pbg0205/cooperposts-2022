package com.cooper.cooperposts2022.users.dto;

import com.cooper.cooperposts2022.users.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegisterRequestDto {

    private final String username;
    private final String password;

    public User toEntity() {
        return new User(username, password);
    }
}
