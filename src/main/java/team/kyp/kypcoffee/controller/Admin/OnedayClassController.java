package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.domain.admin.OnedayClass;
import team.kyp.kypcoffee.domain.admin.OnedayClassApplierInfo;
import team.kyp.kypcoffee.domain.admin.OnedayClassOpenCommand;
import team.kyp.kypcoffee.domain.admin.OnedayDelete;
import team.kyp.kypcoffee.exception.LastdayException;
import team.kyp.kypcoffee.service.admin.AdminOnedayClassService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OnedayClassController {

    private AdminOnedayClassService adminOnedayClassService;

    public OnedayClassController(AdminOnedayClassService adminOnedayClassService){
        this.adminOnedayClassService = adminOnedayClassService;
    }

    @GetMapping("/adminOnedayClass")
    public String adminOnedayClassForm(@RequestParam(value = "section", defaultValue="1") int section,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = adminOnedayClassService.selectAllNumber();
        List<OnedayClass> list = adminOnedayClassService.selectPaging(new Paging(section, pageNum));
        String totalCntJudge = adminOnedayClassService.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("onedaylist", list);

        return "admin/onedayClass/adminOnedayClass";
    }

    @PostMapping("/adminOnedayClassOpen")
    public String onedayClassRegi(OnedayClassOpenCommand onedayClassOpenCommand, Errors errors) {
        //원데이클래스 오픈
        try{
            adminOnedayClassService.onedayClassOpen(onedayClassOpenCommand);
        }catch (LastdayException e){
            return "registFail";
        }

        return "redirect:/admin/adminOnedayClass";
    }

    @GetMapping("/adminOnedayClass/regist")
    public String onedayClassRegiForm(OnedayClassOpenCommand onedayClassOpenCommand){
        return "admin/onedayClass/onedayClassRegi";
    }

    @GetMapping("/adminOnedayClass/Detail/{classNum}")
    public String onedayClassDetail(@PathVariable("classNum") int classNum, Model model){
        //원데이클래스 신청자 정보
        List<OnedayClassApplierInfo> infoList = adminOnedayClassService.selectClassByNum(classNum);

        model.addAttribute("infoList", infoList);
        return "admin/onedayClass/adminOnedayClassDetail";
    }

    @GetMapping("/adminOnedayClass/delete/{classNum}")
    public String deleteClass(@PathVariable("classNum") int classNum){

        adminOnedayClassService.deleteClass(classNum);
        return "redirect:/admin/adminOnedayClass";
    }

    @GetMapping("/adminOnedayClass/applierDelete/{classNum}/{memberNum}")
    public String onedayClassApplierDelete(@PathVariable("classNum") int classNum, @PathVariable("memberNum") int memberNum){
        //관리자가 신청자 취소 시키기
        adminOnedayClassService.deleteApplierByNum(new OnedayDelete(classNum, memberNum));
        return "redirect:/admin/adminOnedayClass/Detail/"+classNum;
    }
}
