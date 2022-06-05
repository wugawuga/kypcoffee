package team.kyp.kypcoffee.controller;

import java.util.HashMap;
import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.LoginCommand;
import team.kyp.kypcoffee.service.AuthService;
import team.kyp.kypcoffee.service.KakaoService;
import team.kyp.kypcoffee.service.MemberRegisterService;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final AuthService authService;
    private final KakaoService kakaoService;
    private final MemberRegisterService memberRegisterService;


    @RequestMapping("/signin/kakao")
    @ResponseBody
    public HashMap<String, Object> kakao(Model model,
        @RequestBody String accessToken) { //json으로 토큰 받아서 서버에 전송. 가입실행
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", userInfo); // DB에 존재하는 아이디인지?

        return map;
    }

    @RequestMapping("/signin")
    public String login(Model model,
        @CookieValue(value = "rememberId", required = false) Cookie cookie) {

        model.addAttribute("loginCommand", new LoginCommand());

        if (cookie != null) {
            System.out.println("쿠키값" + cookie.getValue());
            String cookieval = cookie.getValue();
            model.addAttribute("cookie", cookieval); //쿠키저장되어있으면 모델에 전달
        }

        return "signin/loginForm";
    }

    @RequestMapping("/signout")
    public String logout(HttpSession session) {
        session.invalidate(); //세션에 저장된 모든 데이터를 제거
        return "redirect:/";
    }

    @GetMapping("/signin/loginFailure") //로그인 실패 페이지
    public String loginFail(Model model) {

        return "signin/loginFailure";
    }

    @GetMapping("/signin/loginSuccess")
    public String loginSuccess(Model model) {

        return "signin/loginSuccess";

    } //로그인 폼으로 이동

    @GetMapping("/signin/googleLogin")
    public String googleLogin(Model model) {

        return "signin/googleLogin";
    } //로그인 폼으로 이동

    @GetMapping("/signin/loginExecute")
    public String loginExecute() {

        return "redirect:/accessError";
    }

    @PostMapping("/signin/loginExecute")
    public String submit(@Valid LoginCommand loginCommand, HttpSession session,
        @RequestParam(value = "rememberlogin", required = false) Boolean rememberlogin,
        HttpServletResponse response, BindingResult bindingResult
    ) {

        if (rememberlogin != null) {// 아이디 비밀번호 기억 체크 되어있다면 쿠키생성
            Cookie rememberId = new Cookie("rememberId", loginCommand.getId());
            rememberId.setMaxAge(60 * 10);
            rememberId.setPath("/");
            response.addCookie(rememberId);

        } else if (rememberlogin == null) {
            Cookie deleteId = new Cookie("rememberId", null);
            deleteId.setMaxAge(0);
            response.addCookie(deleteId);
        }

        try {
            AuthInfo authInfo = authService.authenticate(loginCommand);

            session.setAttribute("authInfo", authInfo);

            return "signin/loginSuccess";

        } catch (Exception e) {
            bindingResult.addError(new FieldError("loginCommand", "id", e.getMessage()));

            return "signin/loginForm";
        }
    }
}