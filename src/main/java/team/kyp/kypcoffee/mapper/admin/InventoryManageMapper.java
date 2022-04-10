package team.kyp.kypcoffee.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.InventoryManageUpdate;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.domain.admin.ProductManageUpdateCommand;

import java.util.List;

@Mapper
public interface InventoryManageMapper {

    List<InventoryManage> selectInventoryList(Paging paging);
    int selectAllCnt();

    void updateProductQuantity(InventoryManageUpdate inventoryManageUpdate);

}
