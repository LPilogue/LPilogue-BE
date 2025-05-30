package com.example.lpiloguebe.service;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.entity.User_prefer;
import com.example.lpiloguebe.exception.GeneralException;
import com.example.lpiloguebe.filter.JWTUtil;
import com.example.lpiloguebe.repository.UserRepository;
import com.example.lpiloguebe.repository.User_preferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public User signup(UserRequestDTO.SignupDTO signupDTO){

        // user 객체 생성
        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(signupDTO.getPassword()))
                .nickname(signupDTO.getNickname())
                .city(signupDTO.getCity())
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
        return savedUser;

    }

    public String signin(UserRequestDTO.SigninDTO signinDTO) {

        // username으로 user 조회
        User user = userRepository.findByUsername(signinDTO.getUsername())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        // 비밀번호 확인
        if (!bCryptPasswordEncoder.matches(signinDTO.getPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.BAD_PASSWORD);
        }

        // 1 시간 동안 유효한 토큰 생성
        return jwtUtil.generateToken(user.getUsername(), 3600000);

    }

    public void checkUsername(String username) {
        userRepository.findByUsername(username).ifPresent(u -> {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        });

    }

    public void checkUsernamePassword(String username, String password) {

        // username 유효성 체크
        boolean usernameValid =true;

        // 길이가 6자 이하인지 확인
        if (username == null || username.length() > 6) {
            usernameValid =false;
        }

        // 영문 대문자 포함 여부
        boolean hasUpperCase = false;
        // 영문 소문자 포함 여부
        boolean hasLowerCase = false;
        // 숫자 포함 여부
        boolean hasDigit = false;

        for (char c : username.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        // 대문자, 소문자, 숫자가 모두 포함되어 있어야 함
        if(!(hasUpperCase && hasLowerCase && hasDigit)) {
            usernameValid =false;
        }

        if(!usernameValid) {
            throw new GeneralException(ErrorStatus.INVALID_USERNAME);
        }

        // 비밀번호 유효성 체크
        boolean passwordValid = true;

        // 길이가 8자 이상인지 확인
        if (password == null || password.length() < 8) {
            passwordValid = false;
        }

        // 영문 대문자 포함 여부
        hasUpperCase = false;
        // 영문 소문자 포함 여부
        hasLowerCase = false;
        // 숫자 포함 여부
        hasDigit = false;
        // 특수문자 포함 여부
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        // 대문자, 소문자, 숫자, 특수문자가 모두 포함되어 있어야 함
        if (!(hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar)) {
            passwordValid = false;
        }

        if (!passwordValid) {
            throw new GeneralException(ErrorStatus.INVALID_PASSWORD);
        }

    }
}

