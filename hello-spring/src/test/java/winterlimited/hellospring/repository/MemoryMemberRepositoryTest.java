package winterlimited.hellospring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import winterlimited.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("winter");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("winter");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("winter2");
        repository.save(member2);

        Member result = repository.findByName("winter1").get();
        assertThat(result).isEqualTo(member1);
    }
}
