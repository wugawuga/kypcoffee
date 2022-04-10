package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.InventoryManageUpdate;
import team.kyp.kypcoffee.service.admin.InventoryManageService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class InventoryManageController {

    private InventoryManageService inventoryManageService;

    public InventoryManageController(InventoryManageService inventoryManageService){
        this.inventoryManageService = inventoryManageService;
    }

    @GetMapping("/inventoryManage")
    public String inventoryManageList(@RequestParam(value = "section", defaultValue="1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = inventoryManageService.selectAllCnt();
        List<InventoryManage> inventoryList = inventoryManageService.selectInventoryList(new Paging(section, pageNum));
        String totalCntJudge = inventoryManageService.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("inventoryList", inventoryList);
        return "admin/inventory/inventoryManageList";
    }

    @GetMapping("/inventoryManage/update/{productCode}/{productQuantity}")
    public String inventoryUpdateDo(@PathVariable("productCode") int productCode,
                                    @PathVariable("productQuantity") int productQuantity){

        System.out.println(productCode);
        System.out.println(productQuantity);

        inventoryManageService.updateProductQuantity(new InventoryManageUpdate(productCode,productQuantity));

        return "redirect:/admin/inventoryManage";
    }


}
