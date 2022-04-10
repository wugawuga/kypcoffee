package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductManage {
    private int productCode;
    private String productName;
    private int productPrice;
    private String imgName;
    private String contentImgName;
    private String productType;
}
