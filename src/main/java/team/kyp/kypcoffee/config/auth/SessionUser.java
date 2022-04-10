package team.kyp.kypcoffee.config.auth;

import lombok.Data;
import lombok.Getter;
import team.kyp.kypcoffee.domain.User.User;

import java.io.Serializable;

@Data
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}