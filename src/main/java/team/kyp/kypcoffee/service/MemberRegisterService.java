package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.mapper.MemberMapper;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

    @Autowired
    MemberMapper mapper;
    private final HttpSession httpSession;

    @Transactional
    public void register(RegisterRequest req){
        Member newMember = new Member(req.getNo(), req.getId(), req.getPw(), req.getName(),req.getBirth(),req.getAddress(),
                req.getTel(), req.getPhone(),req.getEmail(), req.getEmailyn(),req.getPoint());
        Member newMemberInfo = new Member(req.getNo(),req.getType());

        mapper.insertMember(newMember);
        mapper.insertMemberInfo(newMemberInfo);
        httpSession.setAttribute("newMember",newMember);
    }

    @Transactional
    public List<Member> selectByIdList(String memberId) { //리스트로 출력시
       List<Member> list = mapper.selectByIdList(memberId);
       return list;
    }

    @Transactional
    public Member selectByIdAll(String memberId) {
        Member member = mapper.selectByIdAll(memberId);
        return member;
    }

    @Transactional
    public Member selectById(String memberId) {
        Member member = mapper.selectById(memberId);
        return member;
    }

    @Transactional
    public Member selectByEmail(String memberEmail) {
        Member member = mapper.selectByEmail(memberEmail);
        return member;
    }

    @Transactional
    public Member selectByMnum(Integer memberNum) {
        Member member = mapper.selectByMnum(memberNum);
        return member;
    }

    @Transactional
    public void updatePw(RegisterRequest req) {

        Member member = mapper.selectByIdAll(req.getId());
        System.out.println(member.getMemberId()+"멤버 가져오기 테스트");
        member.setMemberPw(req.getPw());

        mapper.updateMember(member);
    }

    @Transactional
    public void update(RegisterRequest req) {

        Member member = mapper.selectByMnum(req.getNo());
        System.out.println(member.getMemberId()+"멤버 가져오기 테스트");
        member.setMemberPw(req.getPw());
        member.setMemberName(req.getName());
        member.setMemberBday(req.getBirth());
        member.setMemberAddress(req.getAddress());
        member.setMemberTel(req.getTel());
        member.setMemberPhone(req.getPhone());
        member.setMemberEmail(req.getEmail());
        member.setMemberEmailYn(req.getEmailyn());

        mapper.updateMember(member);
    }

    @Transactional
    public void updateMember(RegisterRequest req) {

        Member member = mapper.selectByEmailOnly(req.getEmail());
        System.out.println(member.getMemberEmail()+"멤버 가져오기 테스트");

        member.setMemberName(req.getName());
        member.setMemberBday(req.getBirth());
        member.setMemberAddress(req.getAddress());
        member.setMemberTel(req.getTel());
        member.setMemberPhone(req.getPhone());
        member.setMemberEmailYn(req.getEmailyn());

        mapper.updateMemberByEmail(member);
    }

    @Transactional
    public void updateMemberByAdmin(RegisterRequest req) {
        Member member = mapper.selectByMnum(req.getNo());

        member.setMemberName(req.getName());
        member.setMemberBday(req.getBirth());
        member.setMemberAddress(req.getAddress());
        member.setMemberTel(req.getTel());
        member.setMemberPhone(req.getPhone());
        member.setMemberEmailYn(req.getEmailyn());
        member.setMemberMileage(req.getPoint());

        mapper.updateMemberByAdmin(member);
    }

    @Transactional
    public void delete(Integer memberNum) { //아직 테스트 안해봄
        mapper.deleteMember(memberNum);
    }

    @Transactional
    public List<Member> selectAllMember() {
       List<Member> member = mapper.selectAllMember();
       return member;
    }

    @Transactional
    public Member selectByEmailOnly(String memberEmail) {
        Member member = mapper.selectByEmailOnly(memberEmail);

        return member;
    }

    @Transactional
    public Integer pagingCount() {
        Integer count = mapper.pagingCount();
        return count;
    }


    @Transactional
    public Member selectMemberInfoByNum(Integer memberNum) {
        Member member = mapper.selectMemberInfoByNum(memberNum);
        return member;
    }

    @Transactional
    public List<Member> selectMemberListPaging(Paging paging) { //리스트로 출력시
        List<Member> list = mapper.selectMemberListPaging(paging);
        return list;
    }

    @Transactional
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 100) judge = "101";
        if(totalCnt == 100) judge = "100";
        if(totalCnt < 100) judge = "99";

        return judge;
    }


}
