package com.example.demo;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Reader 实现了 UserDetails 接口以及其中的方法，这样 Reader 就能代表 Spring Security里的用户了。
@Entity
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String fullname;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER")); // 授予READER权限
    }

    @Override
    public boolean isAccountNonExpired() { // 判断帐号是否已经过期
        return true; // 不过期
    }

    @Override
    public boolean isAccountNonLocked() { // 判断帐号是否已被锁定
        return true; // 不加锁
    }

    @Override
    public boolean isCredentialsNonExpired() { // 判断用户凭证是否已经过期
        return true; // 不过期
    }

    @Override
    public boolean isEnabled() { // 判断用户帐号是否已启用
        return true; // 不禁用
    }

}
