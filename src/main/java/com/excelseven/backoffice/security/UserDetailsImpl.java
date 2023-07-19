package com.excelseven.backoffice.security;

import com.excelseven.backoffice.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//UserDetailslmpl 객체는 UserDetails라는 Security 내장 객체로부터 상속받은 클래스이다

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }


    //계정이 만료되지 않았는지 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠겨있지 않은지 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //사용자의 자격 증명이 만료되지 않았는지 여부를 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //사용자가 활성화 되었는지 여부를 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
