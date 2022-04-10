package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Comment;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.QnaBoard;

import java.util.List;

@Mapper
public interface QnaBoardMapper {
    List<QnaBoard> selectAllList();
    List<QnaBoard> selectViewByNum(Integer memberNum);
    QnaBoard selectView(Integer qnaBoardNum);

    void insertBoard(QnaBoard qnaBoard);
    void deleteBoard(Integer qnaBoardNum);
    void updateBoard(QnaBoard qnaBoard);

    List<Comment> selectByNum(Integer qnaBoardNum);
    void insertCmt(Comment cmt);
    void deleteCmt(Integer cmtNum);

    List<QnaBoard> selectBoardPaging(Paging paging);
    Integer pagingCount();
    Integer pagingCountSearch(Paging paging);
    List<QnaBoard> selectSearchPaging(Paging paging);
}
