package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.admin.MileageRefund;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.PayInfoCount;
import team.kyp.kypcoffee.domain.Payment;
import team.kyp.kypcoffee.domain.admin.ProductRefund;

import java.util.List;

public interface OrdersManageService {
    List<Payment> selectPaymentList(Paging paging);
    List<PayInfoCount> selectPayCount();

    int selectAllNumber();

    String totalCntJudge(int totalCnt);

    List<Payment> selectPaymentByPayNumber(int payNumber);

    void refundPaymentByPayNumber(MileageRefund mileageRefund);

    String getImpUid(int payNumber);
    int getPrice(int payNumber);

    int getMemberNum(int payNumber);

    void updateQuantitytRefund(int payNumber);

}
