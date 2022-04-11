package team.kyp.kypcoffee.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.kyp.kypcoffee.interceptor.AdminVerification;
import team.kyp.kypcoffee.interceptor.LoginVerification;
import team.kyp.kypcoffee.interceptor.SessionVerification;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HandlerInterceptor loginVerification;

//    private List<String> notLoadList;

    //외부에 있는 상품이미지 저장폴더 경로 설정하기
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // addResourceHandler : 스프링부트에서 확인할 폴더 위치 설정
        // addResourceLocations : 실제 시스템의 폴더 위치

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            registry.addResourceHandler("/productImg/**").addResourceLocations("file:///C:/productImg/");
            registry.addResourceHandler("/reviewImg/**").addResourceLocations("file:///C:/reviewImg/");
        } else if (os.contains("mac")) {
            registry.addResourceHandler("/productImg/**").addResourceLocations("file:/Users/kypcoffee/Downloads/kypProductImg/");
            registry.addResourceHandler("/reviewImg/**").addResourceLocations("file:/Users/kypcoffee/Downloads/kypReviewImg/");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            registry.addResourceHandler("/productImg/**").addResourceLocations("file:/home/ubuntu/kypcoffee/kypProductImg/");
            registry.addResourceHandler("/reviewImg/**").addResourceLocations("file:/home/ubuntu/kypcoffee/kypReviewImg/");
        }

        // 맥북 파일경로 설정
        //registry.addResourceHandler("/productImg/**").addResourceLocations("맥북파일경로");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginVerification())
                .addPathPatterns("/mypage")
                .addPathPatterns("/cartList")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/signin");

        registry.addInterceptor(new AdminVerification())
                .addPathPatterns("/admin/**");

        registry.addInterceptor(new SessionVerification())
                .addPathPatterns("/signin/**");

        /*
         * registry.addInterceptor(commonInterceptor) .addPathPatterns("/**") // 추가할 url
         * 패턴 .excludePathPatterns("/user/**"); // 제외할 url 패턴
         */
    }
}

