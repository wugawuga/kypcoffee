package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesDetail {

    private int productType;
    private String productName;
    private int cartQuantity;
    private int price;
}
