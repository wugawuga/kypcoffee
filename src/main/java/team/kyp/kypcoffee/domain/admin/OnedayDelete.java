package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OnedayDelete {
    private int classNum;
    private int memberNum;

    public OnedayDelete(){};
    public OnedayDelete(int classNum, int memberNum){
        this.classNum = classNum;
        this.memberNum = memberNum;
    }
}
