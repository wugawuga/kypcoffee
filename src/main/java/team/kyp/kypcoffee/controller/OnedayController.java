package team.kyp.kypcoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.exception.CapacityExcessException;
import team.kyp.kypcoffee.service.OnedayClassService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OnedayController {

    private OnedayClassService onedayClassService;

    public OnedayController(OnedayClassService onedayClassService){
        this.onedayClassService = onedayClassService;
    }

    @GetMapping("class")
    public String onedayClassForm(Model model){
        List<OnedayClassNum> classList = onedayClassService.selectOpenClass();

        model.addAttribute("classList", classList);
        return "onedayClass/onedayClass";
    }

    @GetMapping("class/regist/{classNum}")
    public String onedayClassRegistForm(OnedayClassRegiCommand onedayClassRegiCommand,
                                        @PathVariable("classNum") int classNum, HttpSession session, Model model){

        AuthInfo ai = (AuthInfo)session.getAttribute("authInfo");
        if(ai == null){
            return "accessFail";
        }

        Boolean isAlreadyRegiMember = onedayClassService.isAlreadyRegiMember(classNum, ai.getNo());

        model.addAttribute("classNum", classNum);
        model.addAttribute("isAlreadyRegiMember", isAlreadyRegiMember);
        return "onedayClass/onedayClassRegi";
    }

    @PostMapping("class/regist")
    public String onedayClassRegist(OnedayClassRegiCommand onedayClassRegiCommand, Model model){

        try{
            onedayClassService.regiClass(onedayClassRegiCommand);
        }
        catch (CapacityExcessException e){
            String errors = "인원초과입니다.";
            model.addAttribute("errors",errors);
            model.addAttribute("classNum", onedayClassRegiCommand.getClassNum());

            return "onedayClass/onedayClassRegi";
        }

        return "redirect:/class";
    }

}
