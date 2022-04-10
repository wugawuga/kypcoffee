package team.kyp.kypcoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.Review;
import team.kyp.kypcoffee.service.CrawlingService;
import team.kyp.kypcoffee.service.admin.ReviewService;

import java.util.List;

@Controller
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("review")
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

        return "review/reviewList";
    }

    @GetMapping("review/detail/{reviewNum}")
    public String reviewDetail(@PathVariable("reviewNum") int reviewNum, Model model){

        List<Review> list = reviewService.selectReviewByNum(reviewNum);

        model.addAttribute("reviewDetail", list.get(0));

        return "review/reviewDetail";
    }

}
