package team.kyp.kypcoffee.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.domain.admin.ProductManageUpdateCommand;

import java.util.List;

@Mapper
public interface ProductManageMapper {

    List<ProductManage> selectProductList(Paging paging);
    int selectAllCnt();
    List<ProductManage> selectProductDetail(int productCode);

    void delProductByCode(int productCode);
    void delProductInfoByCode(int productCode);

    void updateProduct(ProductManageUpdateCommand productManageUpdateCommand);
    void updateProductInfo(ProductManageUpdateCommand productManageUpdateCommand);
}
