package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.SigninDTO;
import com.example.lpiloguebe.dto.SignupDTO;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.entity.User_prefer;
import com.example.lpiloguebe.exception.BadCredentialsException;
import com.example.lpiloguebe.exception.UsernameAlreadyExistsException;
import com.example.lpiloguebe.exception.UsernameNotFoundException;
import com.example.lpiloguebe.filter.JWTUtil;
import com.example.lpiloguebe.repository.UserRepository;
import com.example.lpiloguebe.repository.User_preferRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final User_preferRepository user_preferRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JWTUtil jwtUtil;

    public void signup(SignupDTO signupDTO){

        // user 객체 생성
        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(signupDTO.getPassword()))
                .nickname(signupDTO.getNickname())
                .build();

        // user 저장
        User savedUser = userRepository.save(user);

        // user_prefer 객체 생성
        User_prefer user_prefer = User_prefer.builder()
                .user(savedUser)
                .sad(signupDTO.getSad())
                .happy(signupDTO.getHappy())
                .stressed(signupDTO.getStressed())
                .lonely(signupDTO.getLonely())
                .artist(signupDTO.getArtist())
                .build();

        // user_prefer 저장
        user_preferRepository.save(user_prefer);

        // user와 user_prefer 연결
        savedUser.addUserPrefer(user_prefer);

        log.info("User {} 저장 완료 {}", savedUser.getUsername(), savedUser.toString());

    }

    public String signin(SigninDTO signinDTO) {

        // username으로 user 조회
        User user = userRepository.findByUsername(signinDTO.getUsername());

        // user가 없으면 예외 처리
        if (user == null) {
            throw new UsernameNotFoundException("사용자 정보가 없습니다.");
        }

        // 비밀번호 확인
        if (!bCryptPasswordEncoder.matches(signinDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 1 시간 동안 유효한 토큰 생성
        return jwtUtil.generateToken(user.getUsername(), 3600000);

    }

    public void checkUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new UsernameAlreadyExistsException("이미 존재하는 ID 입니다.");
        }
    }
}

