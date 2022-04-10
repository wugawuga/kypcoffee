package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.admin.SalesDetail;
import team.kyp.kypcoffee.domain.admin.SalesVO;
import team.kyp.kypcoffee.domain.admin.SummarySales;

import java.util.List;
import java.util.Map;

public interface SalesStatusService {
    List<SummarySales> selectSummarySales(String payDate);
    List<SummarySales> selectSummarySalesMonth(String payDate);
    Map<Integer, List<SalesDetail>> selectSalesDetail(SalesVO salesVO);
}
