package team.kyp.kypcoffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.NoticeCommand;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.mapper.NoticeMapper;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeMapper mapper;

    @Override
    public List<Notice> selectAllNotice() {
        return mapper.selectAll();
    }

    @Override
    public List<Notice> selectPaging(Paging paging) {
        return mapper.selectPaging(paging);
    }

    @Override
    public int selectAllNumber() {
        return mapper.selectAllNumber();
    }

    @Override
    public String totalCntJudge(int totalCnt) {
        String judge = "";
        if(totalCnt > 100) judge = "101";
        if(totalCnt == 100) judge = "100";
        if(totalCnt < 100) judge = "99";

        return judge;
    }

    @Override
    public void noticeRegist(NoticeCommand noticeCommand) {
        mapper.insertAdminNotice(noticeCommand);
    }

    @Override
    public void noticeViewCntInc(int noticeNum) {
        mapper.viewCntInc(noticeNum);
    }

    @Override
    public List<Notice> selectByNoticeNum(int noticeNum) {
        return mapper.selectByNoticeNum(noticeNum);
    }

    @Override
    public void noticeDelete(int noticeNum) {
       mapper.deleteByNoticeNum(noticeNum);
    }

    @Override
    public void updateByNoticeNum(NoticeCommand noticeCommand) {
        mapper.updateByNoticeNum(noticeCommand);
    }

}
