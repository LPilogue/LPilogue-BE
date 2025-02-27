package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.CustomUserDetails;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("username: {} is not found", username);
            throw new UsernameNotFoundException(username + " is not found");
        }
        return new CustomUserDetails(user);
    }
}
