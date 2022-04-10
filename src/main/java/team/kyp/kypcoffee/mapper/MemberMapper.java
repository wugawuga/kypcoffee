package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.QnaBoard;
import team.kyp.kypcoffee.domain.User.Kakao;
import team.kyp.kypcoffee.domain.User.User;
import team.kyp.kypcoffee.domain.Member;

import java.util.List;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    void insertMemberInfo(Member member);

    void updatePw(Member member);
    void updateMember(Member member);
    void updateMemberByEmail(Member member);
    void updateMemberByAdmin(Member member);

    void deleteMember(Integer memberNum);

    List<Member> selectByIdList(String memberId);
    List<Member> selectAllMember();

    Member selectMemberInfoByNum(Integer memberNum);
    Member selectById(String memberId);
    Member selectByIdAll(String id);
    Member selectByMnum(Integer memberNum);
    Member selectByEmail(String memberEmail); //이메일 존재 여부만 판단

    Member selectByEmailOnly(String memberEmail); //이메일로 회원정보 찾기

    User findByEmailGoogle(String email);
    Kakao findByEmailKakao(String email);

    void save(User user); //구글테이블 저장
    void saveKakao(Kakao kakao); //카카오 테이블 저장

    List<Member> selectMemberListPaging(Paging paging);
    Integer pagingCount();

}
