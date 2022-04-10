package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.domain.admin.ProductManageUpdateCommand;

import java.util.List;

public interface ProductManageService {

    List<ProductManage> selectProductList(Paging paging);
    int selectAllCnt();
    String totalCntJudge(int totalCnt);

    List<ProductManage> selectProductDetail(int productCode);
    void selectProductDelete(int productCode);

    void productUpdate(ProductManageUpdateCommand productManageUpdateCommand);


}
