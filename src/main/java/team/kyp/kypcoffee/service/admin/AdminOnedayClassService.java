package team.kyp.kypcoffee.service.admin;

import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.domain.admin.OnedayClass;
import team.kyp.kypcoffee.domain.admin.OnedayClassApplierInfo;
import team.kyp.kypcoffee.domain.admin.OnedayClassOpenCommand;
import team.kyp.kypcoffee.domain.admin.OnedayDelete;

import java.util.List;

public interface AdminOnedayClassService {

    void onedayClassOpen(OnedayClassOpenCommand onedayClassOpenCommand);

    List<OnedayClass> selectPaging(Paging paging);

    int selectAllNumber();

    String totalCntJudge(int totalCnt);

    List<OnedayClassApplierInfo> selectClassByNum(int classNum);

    void deleteApplierByNum(OnedayDelete onedayDelete);

    void deleteClass(int classNum);




}
