package com.excelseven.backoffice.entity;

import com.excelseven.backoffice.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false, unique = true)
    private String email;

    public User(SignupRequestDto requestDto, String password) {
        this.username = requestDto.getUsername();
        this.password = password;
    }
//
//    @Column(nullable = false)
//    private String nickName;




}
