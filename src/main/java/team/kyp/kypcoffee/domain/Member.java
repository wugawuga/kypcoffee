package team.kyp.kypcoffee.domain;

import lombok.Data;
import team.kyp.kypcoffee.exception.IdPasswordNotMatchingException;

import java.util.Date;

@Data
public class Member {
    private int memberNum;
    private String memberId;
    private String memberPw;
    private String memberName;
    private Date memberBday;
    private String memberAddress;
    private String memberTel;
    private String memberPhone;
    private String memberEmail;
    private int memberEmailYn;
    private int memberMileage;
    private int memberType;

    public Member() {}

    public Member(String memberPw) {
        this.memberPw = memberPw;
    }

    public Member(int memberNum, String memberId, String memberPw, String memberName, Date memberBday,
                  String memberAddress, String memberTel, String memberPhone, String memberEmail, int memberEmailYn, int memberMileage) {
        this.memberNum = memberNum;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberBday = memberBday;
        this.memberAddress = memberAddress;
        this.memberTel = memberTel;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberEmailYn = memberEmailYn;
        this.memberMileage = memberMileage;
    }

    public Member(int memberNum, int memberType) {
        this.memberNum = memberNum;
        this.memberType = memberType;
    }

    public Member(int memberNum, String memberId, String memberPw, String memberName,
                  Date memberBday, String memberAddress, String memberTel, String memberPhone,
                  String memberEmail, int memberEmailYn, int memberMileage, int memberType) {
        this.memberNum = memberNum;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberBday = memberBday;
        this.memberAddress = memberAddress;
        this.memberTel = memberTel;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberEmailYn = memberEmailYn;
        this.memberMileage = memberMileage;
        this.memberType = memberType;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getMemberBday() {
        return memberBday;
    }

    public void setMemberBday(Date memberBday) {
        this.memberBday = memberBday;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public int getMemberEmailYn() {
        return memberEmailYn;
    }

    public void setMemberEmailYn(int memberEmailYn) {
        this.memberEmailYn = memberEmailYn;
    }

    public int getMemberMileage() {
        return memberMileage;
    }

    public void setMemberMileage(int memberMileage) {
        this.memberMileage = memberMileage;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }
}
