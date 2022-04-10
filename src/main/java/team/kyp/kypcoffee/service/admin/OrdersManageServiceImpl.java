package team.kyp.kypcoffee.service.admin;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.admin.MileageRefund;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.PayInfoCount;
import team.kyp.kypcoffee.domain.Payment;
import team.kyp.kypcoffee.domain.admin.ProductRefund;
import team.kyp.kypcoffee.mapper.admin.OrdersManageMapper;

import java.util.List;

@Service
public class OrdersManageServiceImpl implements OrdersManageService{

    private OrdersManageMapper mapper;

    public OrdersManageServiceImpl(OrdersManageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Payment> selectPaymentList(Paging paging) {
        return mapper.selectPaymentList(paging);
    }

    @Override
    public List<PayInfoCount> selectPayCount() {
        return mapper.selectPayCount();
    }

    @Override
    public int selectAllNumber() {
        return mapper.selectAllNumber();
    }

    @Override
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 50) judge = "51";
        if(totalCnt == 50) judge = "50";
        if(totalCnt < 50) judge = "49";

        return judge;
    }

    @Override
    public List<Payment> selectPaymentByPayNumber(int payNumber) {
        return mapper.selectPaymentByPayNumber(payNumber);
    }

    @Override
    public void refundPaymentByPayNumber(MileageRefund mileageRefund) {
        mapper.updatePaymentByPayNumber(mileageRefund.getPayNumber());
        mapper.updateMileageForRefund(mileageRefund);
        mapper.saveMileageRefund(mileageRefund);
    }

    @Override
    public String getImpUid(int payNumber) {
        return mapper.selectImpuidByPayNumber(payNumber);
    }

    @Override
    public int getPrice(int payNumber) {
        return mapper.selectPriceByPaynumber(payNumber);
    }

    @Override
    public int getMemberNum(int payNumber) {
        return mapper.selectMemberNumByPayNumber(payNumber);
    }

    @Override
    public void updateQuantitytRefund(int payNumber) {
        List<ProductRefund> refundLists = mapper.selectProductInfoByPayNumber(payNumber);

        for (ProductRefund list : refundLists) {
            System.out.println("환불들어갑니다");
            mapper.updateQuantitytRefund(list);
        }
    }

}
