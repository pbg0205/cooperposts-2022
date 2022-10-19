package com.cooper.cooperposts2022.users.service;

import com.cooper.cooperposts2022.users.domain.CustomUserDetails;
import com.cooper.cooperposts2022.users.domain.User;
import com.cooper.cooperposts2022.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username is not matched"));
        return new CustomUserDetails(user);
    }

}
