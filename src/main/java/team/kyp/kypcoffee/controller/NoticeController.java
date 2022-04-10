package team.kyp.kypcoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.service.NoticeServiceImpl;

import java.util.List;

@Controller
public class NoticeController {

    private NoticeServiceImpl noticeService;

    public NoticeController(NoticeServiceImpl noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("notice")
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

        return "notice/noticeList";
    }

    @GetMapping("notice/detail/{noticeNum}")
    public String noticeDetail(@PathVariable("noticeNum") int noticeNum,
                               @ModelAttribute Notice notice, Model model){
        noticeService.noticeViewCntInc(noticeNum);
        List<Notice> list = noticeService.selectByNoticeNum(noticeNum);

        model.addAttribute("noticeList", list.get(0));
        return "notice/noticeDetail";

    }
}
