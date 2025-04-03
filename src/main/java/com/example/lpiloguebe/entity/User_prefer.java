package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User_prefer extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int happy;

    @Column(nullable=false)
    private int sad;

    @Column(nullable=false)
    private int stressed;

    @Column(nullable=false)
    private int lonely;

    @Column(nullable=false)
    private String artist;

    @JoinColumn(name = "userId")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
