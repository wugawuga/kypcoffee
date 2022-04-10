package team.kyp.kypcoffee.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {

    private int cartNum;
    private int memberNum;
    private int productCode;
    private String email;
    private int cartQuantity;
    private String productName;
    private int productPrice;
    private int totalPrice;
    private String productImg;
    private String imgName;

    public Cart(int productCode, int cartQuantity) {
        this.productCode = productCode;
        this.cartQuantity = cartQuantity;
    }
}
