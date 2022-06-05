package team.kyp.kypcoffee.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Valid
@Setter
@Getter
public class LoginCommand {

    @NotEmpty(message = "아이디가 입력되지 않았어요")
    private String id;

    @NotEmpty(message = "비밀번호가 입력되지 않았어요")
    private String pw;
    private String name;
    private int no;
    private int type;
}