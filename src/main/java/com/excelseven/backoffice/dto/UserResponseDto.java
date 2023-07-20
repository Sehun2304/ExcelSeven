package com.excelseven.backoffice.dto;

import com.excelseven.backoffice.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String email;
    private String username;
    private String introduce;


    public UserResponseDto(User users){
        this.id = users.getId();
        this.email = users.getEmail();
        this.username = users.getUsername();
        this.introduce = users.getIntroduce();

    }
}
