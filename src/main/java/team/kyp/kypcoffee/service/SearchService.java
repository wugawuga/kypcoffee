package team.kyp.kypcoffee.service;

import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.QnaBoard;
import team.kyp.kypcoffee.domain.admin.Review;

import java.util.List;

public interface SearchService {
    List<Product_info> productSearchList(String keyWord);
    List<Notice> noticeSearchList(String keyWord);
    List<QnaBoard> qnaSearchList(String keyWord);
    List<Review> reviewSearchList(String keyWord);
    List<OnedayClassNum> onedayclassSearchList(String keyWord);
}
