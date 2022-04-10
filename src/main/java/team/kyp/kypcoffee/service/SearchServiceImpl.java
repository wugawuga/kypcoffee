package team.kyp.kypcoffee.service;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.domain.QnaBoard;
import team.kyp.kypcoffee.domain.admin.Review;
import team.kyp.kypcoffee.mapper.SearchMapper;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    private SearchMapper mapper;

    public SearchServiceImpl(SearchMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<Product_info> productSearchList(String keyWord) {
        return mapper.productSearchList(keyWord);
    }

    @Override
    public List<Notice> noticeSearchList(String keyWord) {
        return mapper.noticeSearchList(keyWord);
    }

    @Override
    public List<QnaBoard> qnaSearchList(String keyWord) {
        return mapper.qnaSearchList(keyWord);
    }

    @Override
    public List<Review> reviewSearchList(String keyWord) {
        return mapper.reviewSearchList(keyWord);
    }

    @Override
    public List<OnedayClassNum> onedayclassSearchList(String keyWord) {
        return mapper.onedayclassSearchList(keyWord);
    }
}
