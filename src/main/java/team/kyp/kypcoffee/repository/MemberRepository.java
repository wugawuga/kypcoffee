package team.kyp.kypcoffee.repository;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.kyp.kypcoffee.domain.MemberJpa;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(MemberJpa memberJpa) {
        em.persist(memberJpa);
    }
}
