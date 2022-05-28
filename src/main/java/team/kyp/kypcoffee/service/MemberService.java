package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.kyp.kypcoffee.domain.MemberJpa;
import team.kyp.kypcoffee.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(MemberJpa memberJpa) {

        memberRepository.save(memberJpa);
        return memberJpa.getId();
    }
}