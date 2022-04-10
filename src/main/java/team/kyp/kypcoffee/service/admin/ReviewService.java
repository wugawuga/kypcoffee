package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.InventoryManage;
import team.kyp.kypcoffee.domain.admin.Review;

import java.util.List;

public interface ReviewService {

    List<Review> selectReviewList(Paging paging);
    int selectAllCnt();
    String totalCntJudge(int totalCnt);

    List<Review> selectReviewByNum(int reviewNum);
    void deleteByNum(int reviewNum);

    void deleteAll();

}
