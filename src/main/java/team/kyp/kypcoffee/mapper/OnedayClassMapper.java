package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.*;

import java.util.List;

@Mapper
public interface OnedayClassMapper {

    List<OnedayClassNum> selectOpenClass();

    void regiClass(OnedayClassRegiCommand onedayClassRegiCommand);

    List<OnedayClassNum> selectCntByClassNum(int classNum);

    List<Integer> selectRegiMemberNum(int classNum);

}
