package team.kyp.kypcoffee.service.admin;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.admin.SalesDetail;
import team.kyp.kypcoffee.domain.admin.SalesVO;
import team.kyp.kypcoffee.domain.admin.SummarySales;
import team.kyp.kypcoffee.mapper.admin.SalesStatusMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesStatusServiceImpl implements SalesStatusService{

    private SalesStatusMapper mapper;

    public SalesStatusServiceImpl(SalesStatusMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<SummarySales> selectSummarySales(String payDate) {
        return mapper.selectSummarySales(payDate);
    }

    @Override
    public List<SummarySales> selectSummarySalesMonth(String payDate) {
        return mapper.selectSummarySalesMonth(payDate);
    }

    @Override
    public Map<Integer, List<SalesDetail>> selectSalesDetail(SalesVO salesVO) {
        Map<Integer, List<SalesDetail>> map = new HashMap<>();

        List<SalesDetail> coffeeList = new ArrayList<>();
        List<SalesDetail> tumblerList = new ArrayList<>();
        List<SalesDetail> cupList = new ArrayList<>();
        List<SalesDetail> goodsList = new ArrayList<>();
        List<SalesDetail> totalList = new ArrayList<>();

        List<SalesDetail> salesList = mapper.selectSalesDetail(salesVO);

        for (SalesDetail list : salesList) {
            switch (list.getProductType()) {
                case 1:
                    coffeeList.add(list);
                    break;
                case 2:
                    tumblerList.add(list);
                    break;
                case 3:
                    cupList.add(list);
                    break;
                case 4:
                    goodsList.add(list);
                    break;
                case -1:
                    totalList.add(list);
                    break;
                default:
                    break;
            }
        }
        map.put(1, coffeeList);
        map.put(2, tumblerList);
        map.put(3, cupList);
        map.put(4, goodsList);
        map.put(5, totalList);

        return map;
    }
}
