package team.kyp.kypcoffee.service;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.OnedayClassNum;
import team.kyp.kypcoffee.domain.OnedayClassRegiCommand;
import team.kyp.kypcoffee.exception.CapacityExcessException;
import team.kyp.kypcoffee.mapper.OnedayClassMapper;

import java.util.List;

@Service
public class OnedayClassServiceImpl implements OnedayClassService{

    private OnedayClassMapper mapper;

    public OnedayClassServiceImpl(OnedayClassMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<OnedayClassNum> selectOpenClass() {
        return mapper.selectOpenClass();
    }

    @Override
    public void regiClass(OnedayClassRegiCommand onedayClassRegiCommand){
        List<OnedayClassNum> list = mapper.selectCntByClassNum(onedayClassRegiCommand.getClassNum());
        int capacity = list.get(0).getClassCapacity();
        int applicantsNum = list.get(0).getClassApplicantsNum();

        if (applicantsNum + onedayClassRegiCommand.getClassApplicantsNum() > capacity){
            throw new CapacityExcessException();
        }
        mapper.regiClass(onedayClassRegiCommand);
    }

    @Override
    public boolean isAlreadyRegiMember(int classNum, int authMemberNum) {
        boolean isAlreadyMember = false;
        List<Integer> memberList = mapper.selectRegiMemberNum(classNum);

        for (int x : memberList) {
            if(authMemberNum == x) isAlreadyMember = true;
        }

        return isAlreadyMember;
    }
}
