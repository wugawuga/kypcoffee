package team.kyp.kypcoffee.controller.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.domain.admin.MileageRefund;
import team.kyp.kypcoffee.service.IamportService;
import team.kyp.kypcoffee.service.admin.OrdersManageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class OrdersManageController {

    private final OrdersManageService ordersManageService;
    private final IamportService iamportService;

    @GetMapping("/ordersManage")
    public String ordersList(@RequestParam(value = "section", defaultValue="1") int section,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = ordersManageService.selectAllNumber();
        String totalCntJudge = ordersManageService.totalCntJudge(totalCnt);

        List<Payment> payments = ordersManageService.selectPaymentList(new Paging(section, pageNum));
        List<PayInfoCount> infoCounts = ordersManageService.selectPayCount();

        model.addAttribute("payment", payments);
        model.addAttribute("payCount", infoCounts);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);

        return "admin/ordersManage/ordersManage";
    }

    @GetMapping("/ordersManage/detail/{payNumber}")
    public String ordersDetail(@PathVariable("payNumber") int payNumber, Model model){

        List<Payment> paymentList = ordersManageService.selectPaymentByPayNumber(payNumber);

        model.addAttribute("list",paymentList);
        return "admin/ordersManage/ordersManageDetail";
    }

    @GetMapping("/ordersManage/refund/{payNumber}")
    public String ordersRefund(@PathVariable("payNumber") int payNumber){


        String imp_uid = ordersManageService.getImpUid(payNumber);
        int memberNum = ordersManageService.getMemberNum(payNumber);
        //int returnPrice = ordersManageService.getPrice(payNumber);

        iamportService.cancleBuy(imp_uid, 0); // 환불
        ordersManageService.refundPaymentByPayNumber(new MileageRefund(payNumber, memberNum)); // 마일리지 원상복구
        ordersManageService.updateQuantitytRefund(payNumber);

        return "redirect:/admin/ordersManage";
    }
}
