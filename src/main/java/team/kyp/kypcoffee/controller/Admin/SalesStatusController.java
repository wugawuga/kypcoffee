package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.admin.SalesDetail;
import team.kyp.kypcoffee.domain.admin.SalesVO;
import team.kyp.kypcoffee.domain.admin.SummarySales;
import team.kyp.kypcoffee.service.admin.SalesStatusService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class SalesStatusController {

    private SalesStatusService salesStatusService;

    public SalesStatusController(SalesStatusService salesStatusService){
        this.salesStatusService = salesStatusService;
    }

    @GetMapping("/salesStatus")
    public String salesStatusDay(@RequestParam(value = "payDate", defaultValue="1") String payDate, Model model){

        if(payDate.equals("1")){
            payDate =  LocalDate.now().toString();
        }

        List<SummarySales> salesList = salesStatusService.selectSummarySales(payDate);

        model.addAttribute("salesList", salesList);
        model.addAttribute("payDate", payDate);

        return "admin/salesStatus/salesStatusDay";
    }

    @GetMapping("/salesStatus/month")
    public String salesStatusMonth(@RequestParam(value = "payDate", defaultValue="1") String payDate, Model model){

        if(payDate.equals("1")){
            payDate =  LocalDate.now().toString().substring(0,7);
        }

        List<SummarySales> salesList = salesStatusService.selectSummarySalesMonth(payDate);

        model.addAttribute("salesList", salesList);
        model.addAttribute("payDate", payDate);

        return "admin/salesStatus/salesStatusMonth";
    }

    @GetMapping("/salesStatus/detail/{payDate}")
    public String salesStatusDetail(@PathVariable("payDate") String payDate, Model model){

        boolean isPayDateMonth = false;
        if(payDate.length() == 7) isPayDateMonth = true;

        Map<Integer, List<SalesDetail>> salesMap = salesStatusService.selectSalesDetail(new SalesVO(payDate, isPayDateMonth));

        List<SalesDetail> coffeeList = salesMap.get(1);
        List<SalesDetail> cupTumblerList = salesMap.get(2);
        List<SalesDetail> syrupList = salesMap.get(3);
        List<SalesDetail> giftSetList = salesMap.get(4);
        List<SalesDetail> totalList = salesMap.get(5);

        model.addAttribute("payDate", payDate);
        model.addAttribute("coffeeList", coffeeList);
        model.addAttribute("cupTumblerList", cupTumblerList);
        model.addAttribute("syrupList", syrupList);
        model.addAttribute("giftSetList", giftSetList);
        model.addAttribute("totalList", totalList);

        return "admin/salesStatus/salesStatusDetail";
    }
}
