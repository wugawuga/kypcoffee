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
public class AdminVerification implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AdminVerification.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("AdminAccess - {}", "AdminVerification 접근");

        HttpSession session = request.getSession();

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

        if(authInfo.getType() != 0){
            response.sendRedirect("/accessError");
            return false;
        }else{
            session.setMaxInactiveInterval(30*60);
            return true;
        }
    }
}
