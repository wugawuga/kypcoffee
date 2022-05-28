package team.kyp.kypcoffee.web;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class MemberForm {

    private String memberId;

    private String memberPw;

    private String memberName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBday;

    private String memberAddress;

    private String memberTel;
    private String memberPhone;

    private String memberEmail;

    private int memberEmailYn;

    private int memberType;
}
