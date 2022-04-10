package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.NoticeCommand;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.service.NoticeServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class NoticeManageController {

    private NoticeServiceImpl noticeService;

    public NoticeManageController(NoticeServiceImpl noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("/adminNotice")
    public String adminNotice(@RequestParam(value = "section", defaultValue="1") int section,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {

        int totalCnt = noticeService.selectAllNumber();
        List<Notice> list = noticeService.selectPaging(new Paging(section, pageNum));
        String totalCntJudge = noticeService.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("noticeList", list);

        return "admin/notice/noticeList";
    }

    @GetMapping("/adminNotice/regist")
    public String adminNoticeRegiForm(@ModelAttribute NoticeCommand noticeCommand){
        return "admin/notice/noticeRegi";
    }

    @PostMapping("/adminNotice/regist")
    public String adminNoticeRegiAction(@ModelAttribute NoticeCommand noticeCommand){
        noticeService.noticeRegist(noticeCommand);
        return "redirect:/admin/adminNotice";
    }

    @GetMapping("/adminNotice/detail/{noticeNum}")
    public String adminNoticeDetail(@PathVariable("noticeNum") int noticeNum,
                                    @ModelAttribute Notice notice, Model model) {
        noticeService.noticeViewCntInc(noticeNum);
        List<Notice> list = noticeService.selectByNoticeNum(noticeNum);

        model.addAttribute("noticeList", list.get(0));
        return "admin/notice/noticeDetail";
    }

    @GetMapping("/adminNotice/delete/{noticeNum}")
    public String adminNoticeDelete(@PathVariable("noticeNum") int noticeNum){
        noticeService.noticeDelete(noticeNum);
        return "redirect:/admin/adminNotice";
    }

    @PostMapping("/adminNotice/update")
    public String adminNoticeUpdate(@ModelAttribute NoticeCommand noticeCommand){
        noticeService.updateByNoticeNum(noticeCommand);
        return "redirect:/admin/adminNotice";
    }

}
