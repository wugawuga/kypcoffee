package team.kyp.kypcoffee.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MemberJpa {

    @Id
    @GeneratedValue
    @Column(name = "memberNum")
    private int memberNum;

    private String memberId;
    private String memberPw;
    private String memberName;
    private Date memberBday;

    @Embedded
    private AddressJpa addressJpa;

    private String memberTel;
    private String memberPhone;
    private String memberEmail;
    private int memberEmailYn;
    private int memberMileage;
    private int memberType;
}
