package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.AddressJpa;
import team.kyp.kypcoffee.domain.MemberInfoJpa;
import team.kyp.kypcoffee.domain.MemberJpa;
import team.kyp.kypcoffee.web.MemberForm;

@Service
@RequiredArgsConstructor
public class MemberDtoService {

    public MemberJpa change(MemberForm memberForm) {

        return MemberJpa.create(
            memberForm.getMemberId(),
            memberForm.getMemberPw(),
            memberForm.getMemberName(),
            memberForm.getMemberBday(),
            new AddressJpa(memberForm.getMemberAddress()),
            memberForm.getMemberTel(),
            memberForm.getMemberPhone(),
            memberForm.getMemberEmail(),
            memberForm.getMemberEmailYn(),
            new MemberInfoJpa(memberForm.getMemberType())
        );
    }
}