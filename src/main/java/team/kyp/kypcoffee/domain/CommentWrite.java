package team.kyp.kypcoffee.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentWrite {
    private String content;
    private Integer cno;
    private Integer bno;
    private Timestamp cdate;
}
