package com.metacoding.authblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Entity // 필요한 어노테이션 생각 잘하시오
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 시큐리티가 인식하게 하려면 username과 password라는 필드 이름을 써야한다.
    // 필드 이름 다르면 getUsername(), getPassword()를 따로 적어야 한다.
    @Column(unique = true, nullable = false)
    private String username; // getter 필요
    @Column(nullable = false)
    private String password; // getter 필요
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;

    // 권한 확인(role -> admin, manager, guest 같은), 반환이 List인 이유는 권한이 여러 개 일수도 있어서
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 계정이 만료되지 않았는지 확인합니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ID 잠금 되었는지(비밀번호 많이 틀려서 같은)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 관리(법으로 되어있다.)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 비활성화(게임에서 영정 당하는 거)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
