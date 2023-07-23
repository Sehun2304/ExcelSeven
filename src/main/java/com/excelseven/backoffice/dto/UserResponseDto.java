package com.excelseven.backoffice.dto;

import com.excelseven.backoffice.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private String introduce;
    private String email;


    public UserResponseDto(User users){
        this.username = users.getUsername();
        this.introduce = users.getIntroduce();
        this.email = users.getEmail();

    }
}
