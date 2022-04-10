package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.http.Path;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.Review;
import team.kyp.kypcoffee.service.CrawlingService;
import team.kyp.kypcoffee.service.FileUploadService;
import team.kyp.kypcoffee.service.admin.ReviewService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReviewManageController {

    private CrawlingService crawlingService;
    private ReviewService reviewService;
    private FileUploadService fileUploadService;

    public ReviewManageController(CrawlingService crawlingService, ReviewService reviewService, FileUploadService fileUploadService){
        this.crawlingService = crawlingService;
        this.reviewService = reviewService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/reviewManage")
    public String reviewManageList(@RequestParam(value = "section", defaultValue="1") int section,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = reviewService.selectAllCnt();
        List<Review> reviewList = reviewService.selectReviewList(new Paging(section, pageNum));
        String totalCntJudge = reviewService.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("reviewList", reviewList);

        return "admin/review/reviewManage";
    }

    @GetMapping("/reviewManage/regist")
    public String reviewRegi(){
        reviewService.deleteAll();
        crawlingService.instaKypCoffee();

        return "redirect:/admin/reviewManage";
    }

    @GetMapping("/reviewManage/detail/{reviewNum}")
    public String reviewDetail(@PathVariable("reviewNum") int reviewNum, Model model){

        List<Review> list = reviewService.selectReviewByNum(reviewNum);

        model.addAttribute("reviewDetail", list.get(0));

        return "admin/review/reviewManageDetail";
    }

    @GetMapping("/reviewManage/delete/{reviewNum}")
    public String reviewDelete(@PathVariable("reviewNum") int reviewNum){
        reviewService.deleteByNum(reviewNum);

        return "redirect:/admin/reviewManage";
    }

}
