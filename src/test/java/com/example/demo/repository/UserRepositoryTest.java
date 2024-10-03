package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.UserStatus;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(showSql = true)
@Sql("/sql/user-repository-test-data.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given

        // when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByIdAndStatus_로_유저_데이터가_없으면_Optional_empty_를_내려준다() {
        // given

        // when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.INACTIVE);

        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given

        // when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("ppusda@naver.com", UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByEmailAndStatus_로_유저_데이터가_없으면_Optional_empty_를_내려준다() {
        // given

        // when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("ppusda@naver.com", UserStatus.INACTIVE);

        // then
        assertThat(result.isEmpty()).isTrue();
    }
}

/***
 * SQL 어노테이션을 사용하여 미리 작성해 둔 SQL을 통해 테스트를 진행 할 수 있다.
 *
 * JPA로 데이터를 넣고 전처리 (BeforeEach)를 사용하는 것 보다 좋을까??
 *  - 물론 SQL을 직접 작성해야한다는 단점이 생기지만 속도 측면에서 더 빠를 수 밖에 없다.
 *  - 하지만 DB가 바뀐다거나 코드 유지보수 등으로 생각했을 때는 JPA가 더 유리할 것 같다.
 *  - 상황에 맞게끔 사용하는게 좋을 것 같다.
 */