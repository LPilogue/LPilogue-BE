package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.SignupDTO;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signup(SignupDTO signupDTO){

        // username 중복 체크
        if(userRepository.findByUsername(signupDTO.getUsername()) != null){
            log.info("이미 존재하는 username 입니다.");
            return null;
        }

        // user 객체 생성
        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(signupDTO.getPassword()))
                .nickname(signupDTO.getNickname())
                .build();

        // user 저장
        User savedUser = userRepository.save(user);
        log.info("user 정보: {}", savedUser.toString());
        return savedUser;
    }
}

