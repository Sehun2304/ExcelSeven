package com.excelseven.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePswdRequestDto {
    private String password;
    private String newPassword;
}
