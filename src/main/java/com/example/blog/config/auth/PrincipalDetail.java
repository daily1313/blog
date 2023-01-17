package com.example.blog.config.auth;

import com.example.blog.domain.User;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장함
@Getter
public class PrincipalDetail implements UserDetails {

    private User user; // 컴포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }



    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 계정이 만료되지 않았는지 리턴한다. (true : 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다.

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정이 만료되지 않았는지 리턴한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화인지 리턴한다.
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> { return user.getRole().toString();});
        return collection;
    }
}
