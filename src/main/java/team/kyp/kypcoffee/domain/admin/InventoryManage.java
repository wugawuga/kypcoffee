package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryManage {
    private int productCode;
    private String productName;
    private String imgName;
    private String productType;
    private int productQuantity;
}
