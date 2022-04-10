package team.kyp.kypcoffee.domain;

import lombok.Data;


@Data
public class QnaBoardWrite {
    private String title;
    private String content;
    private Integer mno;
    private Integer cno;
    private Integer bno;
}
