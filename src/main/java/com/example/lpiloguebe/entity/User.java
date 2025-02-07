package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@ToString
public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String phone;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaryList = new ArrayList<>();

    @Builder
    public User(String username, String password, String email, String name, String phone, Gender gender, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
    }

}
