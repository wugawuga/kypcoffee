package team.kyp.kypcoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.QnaBoard;
import team.kyp.kypcoffee.domain.admin.Review;
import team.kyp.kypcoffee.service.SearchService;

import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping(value = {"searchDo/","searchDo/{keyWord}"})
    public String searchDo(@PathVariable(required = false)String keyWord, Model model){
        if(keyWord == null){
            keyWord = "";
        }

        System.out.println(keyWord);

        List<Product_info> productSearchList = searchService.productSearchList(keyWord);
        List<Notice> noticeSearchList = searchService.noticeSearchList(keyWord);
        List<QnaBoard> qnaSearchList = searchService.qnaSearchList(keyWord);
        List<Review> reviewSearchList = searchService.reviewSearchList(keyWord);
        List<OnedayClassNum> onedayclassSearchList = searchService.onedayclassSearchList(keyWord);

        model.addAttribute("productSearchList", productSearchList);
        model.addAttribute("noticeSearchList", noticeSearchList);
        model.addAttribute("qnaSearchList", qnaSearchList);
        model.addAttribute("reviewSearchList", reviewSearchList);
        model.addAttribute("onedayclassSearchList", onedayclassSearchList);

        return "search";
    }
}
