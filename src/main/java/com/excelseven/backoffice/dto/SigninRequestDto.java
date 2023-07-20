package com.excelseven.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequestDto {
    private String username;
    private String password;
}