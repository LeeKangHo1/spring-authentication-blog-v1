package com.metacoding.authblog.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserName_test() {
        // given
        String username = "ssar"; // 성공
//        String username = "hello"; // fail까지 확인해야 테스트 끝\

        // when
//        Optional<User> user = userRepository.findByUsername(username);
        User user = userRepository.findByUsername(username);

        // then, import assertj
//        Assertions.assertThat(user.isPresent()).isTrue();
        Assertions.assertThat(user).isNotNull();
    }
}
