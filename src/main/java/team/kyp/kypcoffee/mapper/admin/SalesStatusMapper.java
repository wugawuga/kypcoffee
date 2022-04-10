package team.kyp.kypcoffee.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.admin.SalesDetail;
import team.kyp.kypcoffee.domain.admin.SalesVO;
import team.kyp.kypcoffee.domain.admin.SummarySales;

import java.util.List;

@Mapper
public interface SalesStatusMapper {
    List<SummarySales> selectSummarySales(String payDate);
    List<SummarySales> selectSummarySalesMonth(String payDate);
    List<SalesDetail> selectSalesDetail(SalesVO salesVO);
}
