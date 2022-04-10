package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.QnaBoard;
import team.kyp.kypcoffee.domain.admin.Review;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Product_info> productSearchList(String keyWord);
    List<Notice> noticeSearchList(String keyWord);
    List<QnaBoard> qnaSearchList(String keyWord);
    List<Review> reviewSearchList(String keyWord);
    List<OnedayClassNum> onedayclassSearchList(String keyWord);

}
