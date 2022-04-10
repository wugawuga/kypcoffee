package team.kyp.kypcoffee.service;

import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.OnedayClassRegiCommand;
import team.kyp.kypcoffee.exception.CapacityExcessException;

import java.util.List;

public interface OnedayClassService {

    List<OnedayClassNum> selectOpenClass();

    void regiClass(OnedayClassRegiCommand onedayClassRegiCommand);

    boolean isAlreadyRegiMember(int classNum, int authMemberNum);


}
