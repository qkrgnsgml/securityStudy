package com.cos.security1.auth;

//로그인 진행완료되면 시큐리티 session을 만듬
//오브젝트 => Authentication 타입 객체(안에 User정보가 있어야됨)
//User 오브젝트 타입 => UserDetails 타입 객체

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private com.cos.security1.model.User user; //컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
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
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //계정 일정 기간 지났니?
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화 됐니?
        //1년동안 로그인 안하면 휴면계정
        //현재시간 - 마지막 로그인시간 이면 return false
        return true;
    }
}
