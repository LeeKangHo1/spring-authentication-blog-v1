package com.metacoding.authblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Entity // 필요한 어노테이션 생각 잘하시오
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 이건 auto-increment로 증가하는 기본키 인덱스 번호, null일 때 insert가 실행되도록 int면 안된다.

    @Column(unique = true, nullable = false)
    private String username; // 이게 보통 사람이 생각하는 id, 보통 unique 걸어서 두 번째 인덱스로 활용(조회할 때 조건(where)으로 쓰이는 값은 인덱스로 만들어야 한다.)
    @Column(nullable = false)
    private String password; // 비밀번호
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;
}
