package com.cooper.cooperposts2022.users.service;

import com.cooper.cooperposts2022.users.domain.Authority;
import com.cooper.cooperposts2022.users.domain.User;
import com.cooper.cooperposts2022.users.dto.UserRegisterRequestDto;
import com.cooper.cooperposts2022.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerUser (UserRegisterRequestDto userRegisterRequestDto) {
        String username = userRegisterRequestDto.getUsername();
        String encodedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
        User user = new User(username, encodedPassword);
        user.assignAuthority(Authority.USER);

        log.debug("registerUser : {}", user);

        User registeredUser = userRepository.save(user);
        return registeredUser.getId();
    }

}
