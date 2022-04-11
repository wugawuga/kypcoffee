package team.kyp.kypcoffee.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import team.kyp.kypcoffee.domain.AuthInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class SessionVerification implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginVerification.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if(authInfo != null) {
            logger.info("LoginInterceptor - {}", authInfo.getId()+" loginpage 호출 ");
        }
        if(authInfo != null) {
            response.sendRedirect("/accessError");
            return false;
        }
        return true;
    }
}
