package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.mapper.ProductListMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListServiceImpl implements ProductListService {

    private final ProductListMapper mapper;

    @Override
    public List<Product_info> findAll() {

        return mapper.selectAll();
    }

    @Override
    public List<Product_info> findType(int productType) {

        return mapper.selectType(productType);
    }

    @Override
    public Product_info detailByCode(Long productCode) {

        return mapper.selectByCode(productCode);
    }

    @Override
    public List<Product_info> selectProductList(Paging paging) {
        return mapper.selectProductList(paging);
    }

    @Override
    public int selectAllCnt() {
        return mapper.selectAllCnt();
    }

    @Override
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 80) judge = "81";
        if(totalCnt == 80) judge = "80";
        if(totalCnt < 80) judge = "79";

        return judge;
    }

    @Override
    public List<Product_info> selectProductListByNum(Paging paging) {
        return mapper.selectProductListByNum(paging);
    }

    @Override
    public int selectAllCntByNum(int productType) {
        return mapper.selectAllCntByNum(productType);
    }
}
