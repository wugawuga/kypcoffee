package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {


    @GetMapping("admin")
    public String AdminMain(){

        return "admin/adminMain";
    }


}
