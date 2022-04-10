package team.kyp.kypcoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import team.kyp.kypcoffee.domain.Mail;
import team.kyp.kypcoffee.service.MailService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
    @RequiredArgsConstructor
    public class MailController {
        private final MailService mailService;

        @PostMapping("/mail")
        public void execMail(@ModelAttribute Mail mail, HttpServletResponse response) throws IOException {
            mailService.mailSend(mail);
            System.out.println("메일보내기 성공");

            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('메일이 성공적으로 발송되었습니다.'); </script>");
            out.println("<script>location.href='/mail' </script>");
            out.flush();

        }

        @GetMapping("/mail")
        public String mail() {

            return "mail";
        }
}
