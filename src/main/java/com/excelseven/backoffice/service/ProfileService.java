package com.excelseven.backoffice.service;

import com.excelseven.backoffice.dto.UpdateProfileRequestDto;
import com.excelseven.backoffice.dto.UpdatePswdRequestDto;
import com.excelseven.backoffice.dto.UserResponseDto;
import com.excelseven.backoffice.entity.User;
import com.excelseven.backoffice.repository.UserRepository;
import com.excelseven.backoffice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service //서비스 클래스로 선언하면 스프링 컨테이너에 빈으로 등록되어서 다른 컴포넌트에서 @Autowired 등의 방법으로 주입받아서 사용 가능
@RequiredArgsConstructor //클래스 필드에 생성자를 자동 생성
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    private User findUser(Long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("잘못된 사용자입니다."));
    }

    //유저 프로필 조회
    public UserResponseDto viewProfile(UserDetailsImpl userDetails) {

        User user = findUser(userDetails.getUser().getId());
        return new UserResponseDto(user);
    }


    public void updateProfile(UserDetailsImpl userDetails, UpdateProfileRequestDto updateProfileRequestDto) {

        User user = findUser(userDetails.getUser().getId());

        //로그인이 되지 않은 상태로 잘못된 접근일 경우
        if (user == null) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }

        //비밀번호 재확인
        if (!passwordEncoder.matches(updateProfileRequestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("입력한 비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호가 일치하면 프로필 수정
        String newIntroduce = updateProfileRequestDto.getIntroduce();
        user.setIntroduce(newIntroduce);

        userRepository.save(user);



    }

    //비밀번호 변경
    public void updatePswd(UserDetailsImpl userDetails, UpdatePswdRequestDto updatePswdRequestDto) {

        // 비밀번호 변경을 위한 기존 비밀번호 재확인
        if (!passwordEncoder.matches(updatePswdRequestDto.getPassword(), userDetails.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");}
        else {

            // 입력한 비밀번호가 기존 비밀번호와 일치하면 새로운 비밀번호로 변경 가능
            User user = findUser(userDetails.getUser().getId());
            user.setPassword(passwordEncoder.encode(updatePswdRequestDto.getNewPassword()));
            userRepository.save(user);
        }
    }

}
