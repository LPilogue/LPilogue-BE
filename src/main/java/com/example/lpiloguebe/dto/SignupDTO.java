package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.Gender;
import lombok.Data;
import lombok.Getter;

@Data
public class SignupDTO {

    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private Gender gender;
    private int age;
}
