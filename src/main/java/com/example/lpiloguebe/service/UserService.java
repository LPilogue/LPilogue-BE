package com.example.lpiloguebe.service;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.entity.User_prefer;
import com.example.lpiloguebe.exception.GeneralException;
import com.example.lpiloguebe.repository.UserRepository;
import com.example.lpiloguebe.repository.User_preferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final User_preferRepository user_preferRepository;

    @Transactional
    public User_prefer updateUser(UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        User_prefer userPrefer = user_preferRepository.findByUser(user);
        log.info("UserPrefer: {}", userPrefer.toString());
        userPrefer.setUserPrefer(userUpdateDTO);
        return user_preferRepository.save(userPrefer);
    }

    public void getUserRecap(int year) {
    }
}
