package team.kyp.kypcoffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.admin.ProductManage;

import java.util.List;

public interface ProductListService {

    List<Product_info> findAll();

    List<Product_info> findType(int productType);

    Product_info detailByCode(Long productCode);

    List<Product_info> selectProductList(Paging paging);
    int selectAllCnt();
    String totalCntJudge(int totalCnt);

    List<Product_info> selectProductListByNum(Paging paging);
    int selectAllCntByNum(int productType);
}
