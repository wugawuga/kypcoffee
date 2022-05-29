package team.kyp.kypcoffee.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AuthInfo { //세션에 로그인 정보 저장

    private String id;
    private String name;
    private int no;
    private String pw;
    private String email;
    private String picture;
    private int type;

    private AuthInfo(String id, String name, String email, int type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public static AuthInfo sessionCreate(String id, String name, String email, int type) {

        return new AuthInfo(id, name, email, type);
    }
}
