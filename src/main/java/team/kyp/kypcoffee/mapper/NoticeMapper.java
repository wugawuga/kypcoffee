package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Notice;
import team.kyp.kypcoffee.domain.NoticeCommand;
import team.kyp.kypcoffee.domain.Paging;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> selectAll();

    List<Notice> selectPaging(Paging paging);

    int selectAllNumber();

    void insertAdminNotice(NoticeCommand noticeCommand);

    void viewCntInc(int noticeNum);

    List<Notice> selectByNoticeNum(int noticeNum);

    void deleteByNoticeNum(int noticeNum);

    void updateByNoticeNum(NoticeCommand noticeCommand);

}
