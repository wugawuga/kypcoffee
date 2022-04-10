package team.kyp.kypcoffee.service.admin;

import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.domain.admin.OnedayClass;
import team.kyp.kypcoffee.domain.admin.OnedayClassApplierInfo;
import team.kyp.kypcoffee.domain.admin.OnedayClassOpenCommand;
import team.kyp.kypcoffee.domain.admin.OnedayDelete;
import team.kyp.kypcoffee.exception.LastdayException;
import team.kyp.kypcoffee.mapper.admin.AdminOnedayClassMapper;
import team.kyp.kypcoffee.service.admin.AdminOnedayClassService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminOnedayClassServiceImpl implements AdminOnedayClassService {

    private AdminOnedayClassMapper mapper;

    public AdminOnedayClassServiceImpl(AdminOnedayClassMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void onedayClassOpen(OnedayClassOpenCommand onedayClassOpenCommand) {

        String regiDate = onedayClassOpenCommand.getClassDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date classDate = dateFormat.parse(regiDate);
            Date todayDate = new Date();

            System.out.println("classDate : " + classDate);
            System.out.println("todayDate : " + todayDate);

            if (classDate.before(todayDate)){
                System.out.println("지난날");
                throw new LastdayException();
            }

        }
        catch (Exception e){
            throw new LastdayException();
        }
        mapper.insertOnedayClassOpen(onedayClassOpenCommand);
    }

    @Override
    public List<OnedayClass> selectPaging(Paging paging) {
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
    public List<OnedayClassApplierInfo> selectClassByNum(int classNum) {
        return mapper.selectClassByNum(classNum);
    }

    @Override
    public void deleteApplierByNum(OnedayDelete onedayDelete) {
        mapper.deleteApplierByNum(onedayDelete);
    }

    @Override
    public void deleteClass(int classNum) {
        mapper.deleteClass(classNum);
    }
}
