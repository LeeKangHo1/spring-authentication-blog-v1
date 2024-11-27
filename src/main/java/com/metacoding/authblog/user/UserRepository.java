package com.metacoding.authblog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findByUsername(String username) {
        // jpql이다, ? 대신 : 을 쓴다.
        Query q = em.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);

        // 조회하면 영속화 된다.
        try {
            return (User) q.getSingleResult(); // 반환형이 옵셔널에서 다시 User로 돌아간다.
//            return Optional.ofNullable(userPS); 이렇게 반환할 필요가 없다. null이면 예외 나오니까
        } catch (RuntimeException e) {
            // return 옵셔널 보다 throw 하는게 좋다.
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
        }
        // 다 만들면 일단 테스트
    }
}
