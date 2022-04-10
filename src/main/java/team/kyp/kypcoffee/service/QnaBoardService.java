package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.mapper.QnaBoardMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaBoardService {

    @Autowired
    QnaBoardMapper qnaBoardMapper;


    @Transactional
    public List<QnaBoard> selectAllList() { //리스트로 출력시
        List<QnaBoard> list = qnaBoardMapper.selectAllList();
        return list;
    }

    @Transactional
    public List<QnaBoard> selectViewByNum(Integer memberNum) { //리스트로 출력시
        List<QnaBoard> list = qnaBoardMapper.selectViewByNum(memberNum);
        return list;
    }


    @Transactional
    public QnaBoard selectView(Integer qnaBoardNum) {
        QnaBoard view = qnaBoardMapper.selectView(qnaBoardNum);
        return view;
    }

    @Transactional
    public void boardWrite(QnaBoardWrite br) {
        QnaBoard newboard = new QnaBoard(0, br.getMno(), br.getTitle(), br.getContent(), null);
        qnaBoardMapper.insertBoard(newboard);
    }
    @Transactional
    public void boardDelete(Integer qnaBoardNum) {
        qnaBoardMapper.deleteBoard(qnaBoardNum);
    }

    @Transactional
    public void boardEdit(QnaBoardWrite br) {

        QnaBoard board = qnaBoardMapper.selectView(br.getBno());
        board.setQnaBoardTitle(br.getTitle());
        board.setQnaBoardContent(br.getContent());

        qnaBoardMapper.updateBoard(board);
    }

    @Transactional
    public List<Comment> cmtList(Integer qnaBoardNum) {
       List<Comment> cmt = qnaBoardMapper.selectByNum(qnaBoardNum);
       return cmt;
    }

    @Transactional
    public void cmtWrite(CommentWrite cmtWrite) {
        Comment cmt = new Comment(0, cmtWrite.getContent(),null,cmtWrite.getBno());
        qnaBoardMapper.insertCmt(cmt);
    }

    @Transactional
    public void cmtDelete(Integer cmtNum) { qnaBoardMapper.deleteCmt(cmtNum); }

    @Transactional
    public Integer pagingCount() {
        Integer count = qnaBoardMapper.pagingCount();
    return count;
    }

    @Transactional
    public Integer pagingCountSearch(Paging paging) {
        Integer count = qnaBoardMapper.pagingCountSearch(paging);
        return count;
    }

    @Transactional
    public List<QnaBoard> selectBoardPaging(Paging paging) { //리스트로 출력시
        List<QnaBoard> list = qnaBoardMapper.selectBoardPaging(paging);
        return list;
    }
    @Transactional
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 100) judge = "101";
        if(totalCnt == 100) judge = "100";
        if(totalCnt < 100) judge = "99";

        return judge;
    }

    @Transactional
    public List<QnaBoard> selectSearchPaging(Paging paging) {
        List<QnaBoard> list = qnaBoardMapper.selectSearchPaging(paging);
        return list;
    }
}
