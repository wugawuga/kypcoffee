package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesVO {
    private String payDate;
    private boolean isPayDateMonth;

    public SalesVO(){}
    public SalesVO(String payDate, boolean isPayDateMonth){
        this.payDate = payDate;
        this.isPayDateMonth = isPayDateMonth;
    }
}
