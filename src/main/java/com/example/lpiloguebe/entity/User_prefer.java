package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(nullable=true)
    private String artist;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public User_prefer(int happy, int sad, int stressed, int lonely, String artist, User user) {
        this.happy = happy;
        this.sad = sad;
        this.stressed = stressed;
        this.lonely = lonely;
        this.artist = artist;
        this.user = user;
    }

    public void setUserPrefer(UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        this.happy = userUpdateDTO.getHappy();
        this.sad = userUpdateDTO.getSad();
        this.stressed = userUpdateDTO.getStressed();
        this.lonely = userUpdateDTO.getLonely();
        this.artist = userUpdateDTO.getArtist();
    }
}
