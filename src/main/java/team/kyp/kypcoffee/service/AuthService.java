package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.exception.IdPasswordNotMatchingException;
import team.kyp.kypcoffee.mapper.MemberMapper;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    MemberMapper mapper;

    public AuthInfo authenticate(String id, String name, int no, String pw) {

        Member member = mapper.selectByIdAll(id);

        if(member==null) {
            throw new IdPasswordNotMatchingException();
        }
        if(!member.getMemberPw().equals(pw)) {
            throw new IdPasswordNotMatchingException();
        }

        return new AuthInfo(member.getMemberId(), member.getMemberName(), member.getMemberNum(), member.getMemberPw(),member.getMemberType());
    }

}
