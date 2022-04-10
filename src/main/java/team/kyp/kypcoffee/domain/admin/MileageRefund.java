package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MileageRefund {
    private int payNumber;
    private int memberNum;

    public MileageRefund(){}
    public MileageRefund(int payNumber, int memberNum){
        this.payNumber = payNumber;
        this.memberNum =memberNum;
    }
}
