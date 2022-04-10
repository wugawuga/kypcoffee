package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.kyp.kypcoffee.domain.Cart;
import team.kyp.kypcoffee.domain.PayInfoCount;
import team.kyp.kypcoffee.domain.Payment;

import java.util.List;

@Mapper
public interface PayMapper {

    void insertPay(Payment payment);

    List<Payment> selectPaymentByMemberNum(int memberNum);

    void insertPayMoreThan(Payment payment);

    List<PayInfoCount> selectPayCount(int memberNum);

    void payRefund(String imp_uid);

    void useMileage(@Param("memberNum") int memberNum,@Param("use_pnt") int use_pnt);

    void saveMileage(@Param("memberNum") int memberNum,@Param("savePnt") int savePnt);

    void minusStock(Cart cart);
}
