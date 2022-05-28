package team.kyp.kypcoffee.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
public class MemberJpa {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "membernum")
    private Long id;

    @Column(name = "memberid")
    private String memberId;

    @Column(name = "memberpw")
    private String memberPw;

    @Column(name = "membername")
    private String memberName;

    @Column(name = "memberbday")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBday;

    @Embedded
    private AddressJpa addressJpa;

    @Embedded
    private MemberInfoJpa memberInfoJpa;

    @Column(name = "membertel")
    private String memberTel;

    @Column(name = "memberphone")
    private String memberPhone;

    @Column(name = "memberemail")
    private String memberEmail;

    @Column(name = "memberemailyn")
    private int memberEmailYn;

    @Column(name = "membermileage")
    private int memberMileage;

    public static MemberJpa create(
        String memberId,
        String memberPw,
        String memberName,
        Date memberBday,
        AddressJpa addressJpa,
        String memberTel,
        String memberPhone,
        String memberEmail,
        int memberEmailYn,
        MemberInfoJpa memberInfoJpa) {

        return new MemberJpa(memberId, memberPw, memberName, memberBday, addressJpa, memberTel,
            memberPhone, memberEmail, memberEmailYn, memberInfoJpa);
    }

    private MemberJpa(String memberId, String memberPw, String memberName, Date memberBday,
        AddressJpa addressJpa, String memberTel, String memberPhone, String memberEmail,
        int memberEmailYn, MemberInfoJpa memberInfoJpa) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberBday = memberBday;
        this.addressJpa = addressJpa;
        this.memberTel = memberTel;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberEmailYn = memberEmailYn;
        this.memberInfoJpa = memberInfoJpa;
    }
}