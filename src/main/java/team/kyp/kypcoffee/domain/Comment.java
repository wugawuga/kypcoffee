package team.kyp.kypcoffee.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Comment {
    private Integer cmtNum;
    private Integer qnaBoardNum;
    private String cmtContent;
    private Date cmtDate;

    public Comment(Integer cmtNum, String cmtContent, Date cmtDate,Integer qnaBoardNum) {
        this.cmtNum = cmtNum;
        this.qnaBoardNum = qnaBoardNum;
        this.cmtContent = cmtContent;
        this.cmtDate = cmtDate;
    }

}
