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
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    // mappedBy에는 반대쪽 entity가 나를 참조하는 필드명이 들어감
    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToOne(mappedBy = "user_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private User_prefer user_prefer;

    @Builder
    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname=nickname;
    }

}
