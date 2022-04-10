package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryManageUpdate {
    private int productCode;
    private int productQuantity;

    public InventoryManageUpdate(){}

    public InventoryManageUpdate(int productCode, int productQuantity){
        this.productCode = productCode;
        this.productQuantity = productQuantity;
    }


}
