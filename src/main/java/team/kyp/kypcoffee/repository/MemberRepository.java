package team.kyp.kypcoffee.repository;

import java.util.List;
import java.util.NoSuchElementException;
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

    public MemberJpa findByMemberid(String memberid) {
        List<MemberJpa> memberJpa = em.createQuery(
                "select m from MemberJpa m where m.memberId = :memberid",
                MemberJpa.class)
            .setParameter("memberid", memberid)
            .getResultList();

        if (memberJpa.size() == 0) {
            throw new NoSuchElementException("아이디가 존재하지 않습니다");
        } else {
            return memberJpa.get(0);
        }
    }
}