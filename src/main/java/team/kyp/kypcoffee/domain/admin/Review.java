package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private int reviewNum;
    private String userName;
    private String reviewContent;
    private String ImgSrc;
    private String fileName;
}
