package team.kyp.kypcoffee.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartCommand {

    private int memberNum;
    private int productCode;
    private int cartQuantity;

}
