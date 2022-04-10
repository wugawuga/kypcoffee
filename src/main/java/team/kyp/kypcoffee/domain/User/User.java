package team.kyp.kypcoffee.domain.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User{
    private String name;
    private String email;
    private Role role;
    private String picture;
    private String id;

    @Builder
    public User(String name, String email,Role role, String picture) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.picture=picture;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

}
