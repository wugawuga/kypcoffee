package team.kyp.kypcoffee.service;

import java.util.Optional;
import javax.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.LoginCommand;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.MemberJpa;
import team.kyp.kypcoffee.exception.IdPasswordNotMatchingException;
import team.kyp.kypcoffee.mapper.MemberMapper;
import team.kyp.kypcoffee.repository.MemberRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    public AuthInfo authenticate(LoginCommand loginCommand) {

        MemberJpa memberJpa = memberRepository.findByMemberid(loginCommand.getId());

        if (!memberJpa.getMemberPw().equals(loginCommand.getPw())) {
            throw new IllegalArgumentException("비밀번호를 확인확인확인");
        }

        return AuthInfo.sessionCreate(memberJpa.getMemberId(),
            memberJpa.getMemberName(),
            memberJpa.getMemberEmail(),
            memberJpa.getMemberInfoJpa().getMemberType());
    }

}