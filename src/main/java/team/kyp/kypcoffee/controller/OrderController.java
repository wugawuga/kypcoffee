package team.kyp.kypcoffee.controller;

import com.siot.IamportRestClient.IamportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.service.IamportService;
import team.kyp.kypcoffee.service.OrderInfoService;
import team.kyp.kypcoffee.service.OrderInfoServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final OrderInfoServiceImpl orderInfoServiceImpl;
    private final IamportService iamportService;

    @Autowired
    public OrderController(OrderInfoServiceImpl orderInfoServiceImpl, IamportService iamportService) {
        this.orderInfoServiceImpl = orderInfoServiceImpl;
        this.iamportService = iamportService;
    }

    @GetMapping("/orderList")
    public String orderGet() {
        return "redirect:/";
    }

    @PostMapping("/orderList")
    public String order(HttpSession session, OrderCommand orderCommand, Model model) {

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        int memberNum = ai.getNo();

        Member member = orderInfoServiceImpl.memberInfoByMemberNum(memberNum);

        ArrayList<Integer> cartNum = orderCommand.getCartNum();

        List<Product_info> pInfos = orderInfoServiceImpl.productInfo(cartNum);

        int totalPrice = 0;

        for (Product_info pInfo : pInfos) {
            totalPrice += pInfo.getProductPrice() * pInfo.getCartQuantity();
        }
        int min = 1000;

        model.addAttribute("min", min);
        model.addAttribute("cartNum", cartNum);
        model.addAttribute("member", member);
        model.addAttribute("pInfos", pInfos);
        model.addAttribute("totalPrice", totalPrice);

        return "orders/order2";
    }

    @GetMapping("/pays/refund")
    public String refunds(@RequestParam(value = "imp_uid") String imp_uid) {

        iamportService.payRefund(imp_uid);

        return "redirect:/mypage";
    }
}
