package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String city;

    // mappedBy에는 반대쪽 entity가 나를 참조하는 필드명이 들어감
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private User_prefer userPrefer;

    @Builder
    public User(String username, String password, String nickname, String city) {
        this.username = username;
        this.password = password;
        this.nickname=nickname;
        this.city=city;
    }

    public void addUserPrefer(User_prefer userPrefer) {
        this.userPrefer = userPrefer;
    }

}
