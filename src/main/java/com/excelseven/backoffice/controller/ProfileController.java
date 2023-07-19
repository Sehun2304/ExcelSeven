package com.excelseven.backoffice.controller;

import com.excelseven.backoffice.dto.UpdateProfileRequestDto;
import com.excelseven.backoffice.dto.UpdatePswdRequestDto;
import com.excelseven.backoffice.dto.UserResponseDto;
import com.excelseven.backoffice.security.UserDetailsImpl;
import com.excelseven.backoffice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class ProfileController {

    private final ProfileService profilerService;

    //프로필 메인
    @GetMapping("/")
    //보안상 entity보다 dto를 사용하는게 바람직하기 때문에 dto를 만들어서 활용
    public UserResponseDto viewProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return profilerService.viewProfile(userDetails);

    }


    //프로필 수정

    @PatchMapping("/update")

    public UserResponseDto updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody UpdateProfileRequestDto updateProfileRequestDto){

        return profilerService.updateProfile(userDetails, updateProfileRequestDto);

    }

    //비밀번호 변경

    @PatchMapping("/pswd")

    public UserResponseDto updatePswd(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdatePswdRequestDto updatePswdRequestDto){

        return profilerService.updatePswd(userDetails, updatePswdRequestDto);
    }
}
