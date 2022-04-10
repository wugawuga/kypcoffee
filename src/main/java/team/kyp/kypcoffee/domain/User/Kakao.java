package team.kyp.kypcoffee.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kakao {
        private String name;
        private String email;
        private String picture;

    public Kakao() {}

    public Kakao(Kakao kakao) {
        this.name = kakao.getName();
        this.email = kakao.getEmail();
        this.picture = kakao.getPicture();
    }
}
