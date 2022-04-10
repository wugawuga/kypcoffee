package team.kyp.kypcoffee.service.admin;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.InventoryManageUpdate;
import team.kyp.kypcoffee.mapper.admin.InventoryManageMapper;

import java.util.List;

@Service
public class InventoryManageServiceImpl implements InventoryManageService {

    private InventoryManageMapper mapper;

    public InventoryManageServiceImpl(InventoryManageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<InventoryManage> selectInventoryList(Paging paging) {
        return mapper.selectInventoryList(paging);
    }

    @Override
    public int selectAllCnt() {
        return mapper.selectAllCnt();
    }

    @Override
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 100) judge = "101";
        if(totalCnt == 100) judge = "100";
        if(totalCnt < 100) judge = "99";

        return judge;
    }

    @Override
    public void updateProductQuantity(InventoryManageUpdate inventoryManageUpdate) {
        mapper.updateProductQuantity(inventoryManageUpdate);
    }
}
