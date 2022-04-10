package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.InventoryManageUpdate;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.domain.admin.ProductManageUpdateCommand;

import java.util.List;

public interface InventoryManageService {

    List<InventoryManage> selectInventoryList(Paging paging);
    int selectAllCnt();
    String totalCntJudge(int totalCnt);

    void updateProductQuantity(InventoryManageUpdate inventoryManageUpdate);
}
