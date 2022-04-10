package team.kyp.kypcoffee.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.User.User;
import team.kyp.kypcoffee.mapper.MemberMapper;
import team.kyp.kypcoffee.service.AuthService;
import team.kyp.kypcoffee.service.GeneratePw;
import team.kyp.kypcoffee.service.MemberRegisterService;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    MemberMapper mapper;

    private final HttpSession httpSession;
    private final GeneratePw generatePw;
    private final MemberRegisterService memberRegisterService;
    private final AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // oauth2 로그인 진행 시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuthAttributes: attribute를 담을 클래스 (개발자가 생성)
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        // SessioUser: 세션에 사용자 정보를 저장하기 위한 DTO 클래스 (개발자가 생성)
        //httpSession.setAttribute("user", new SessionUser(user));

        //AuthInfo에 저장하기

        Member member = memberRegisterService.selectByEmailOnly(user.getEmail());
        AuthInfo authInfo = new AuthInfo(member.getMemberId(), member.getMemberName(), member.getMemberNum(), member.getMemberPw(), user.getEmail(), user.getPicture(), member.getMemberType());
        httpSession.setAttribute("authInfo", authInfo);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }


    private User saveOrUpdate(OAuthAttributes attributes) {
        User user;
        String pw = generatePw.excuteGenerate();
        String ph = new String("010-0000-0000");
        Date bday = new Date("January 1,2022");
        String id = new String("google");
        String address = new String("기본 주소를 설정해 주세요");
        String tel = new String("02-0000-0000");

        if(mapper.findByEmailGoogle(attributes.getEmail())!=null){ //이메일이 존재하면 그냥 이메일로 찾은 google 테이블의 사용자정보 돌려준다
            user=mapper.findByEmailGoogle(attributes.getEmail());
        }
        else {
            user=attributes.toEntity(); //없으면 사용자 추가

            Member newMember = new Member(0, id, pw, user.getName(),bday,address,
                    tel, ph,user.getEmail(), 0,0);
            Member newMemberInfo = new Member(0,1); //회원타입 일반회원:1
            mapper.insertMember(newMember);
            mapper.insertMemberInfo(newMemberInfo);
            mapper.save(user);

            user=mapper.findByEmailGoogle(attributes.getEmail());
        }

        return user;
    }
}
