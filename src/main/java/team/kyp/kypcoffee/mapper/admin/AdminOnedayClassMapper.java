package team.kyp.kypcoffee.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.domain.admin.OnedayClass;
import team.kyp.kypcoffee.domain.admin.OnedayClassApplierInfo;
import team.kyp.kypcoffee.domain.admin.OnedayClassOpenCommand;
import team.kyp.kypcoffee.domain.admin.OnedayDelete;

import java.util.List;

@Mapper
public interface AdminOnedayClassMapper {

    void insertOnedayClassOpen(OnedayClassOpenCommand onedayClassOpenCommand);

    List<OnedayClass> selectPaging(Paging paging);

    int selectAllNumber();

    List<OnedayClassApplierInfo> selectClassByNum(int classNum);

    void deleteApplierByNum(OnedayDelete onedayDelete);

    void deleteClass(int classNum);

}
