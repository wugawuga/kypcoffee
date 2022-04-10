package team.kyp.kypcoffee.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.Review;
import team.kyp.kypcoffee.domain.admin.ReviewRegi;

import java.util.List;

@Mapper
public interface ReviewManageMapper {

    void reviewRegi(ReviewRegi reviewRegi);

    List<Review> reviewList(Paging paging);

    int selectAllCnt();

    List<Review> selectReviewByNum(int reviewNum);

    void deleteByNum(int reviewNum);

    void deleteAll();
}
