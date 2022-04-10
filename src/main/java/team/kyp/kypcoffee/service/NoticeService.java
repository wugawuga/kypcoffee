package team.kyp.kypcoffee.service;

import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.NoticeCommand;
import team.kyp.kypcoffee.domain.Paging;

import java.util.List;

public interface NoticeService {
    List<Notice> selectAllNotice();

    List<Notice> selectPaging(Paging paging);

    int selectAllNumber();

    String totalCntJudge(int totalCnt);

    void noticeRegist(NoticeCommand noticeCommand);

    void noticeViewCntInc(int noticeNum);

    List<Notice> selectByNoticeNum(int noticeNum);

    void noticeDelete(int noticeNum);

    void updateByNoticeNum(NoticeCommand noticeCommand);
}
