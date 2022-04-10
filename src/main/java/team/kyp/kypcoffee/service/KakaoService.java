package team.kyp.kypcoffee.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.User.Kakao;
import team.kyp.kypcoffee.mapper.MemberMapper;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class KakaoService {

    @Autowired
    MemberMapper mapper;

    private final GeneratePw generatePw;
    private final HttpSession httpSession;
    private final MemberRegisterService memberRegisterService;
    private final AuthService authService;

    public HashMap<String, Object> getUserInfo(String access_Token) {

        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

//            userInfo.put("nickname", nickname); //HashMap에 정보 담기
//            userInfo.put("email", email);
//            userInfo.put("profile_image", profile_image);

            //세션 저장히기

            Kakao kakao = new Kakao();
            kakao.setName(nickname);
            kakao.setEmail(email);
            kakao.setPicture(profile_image);

            String pw = generatePw.excuteGenerate();
            String ph = new String("010-0000-0000");
            Date bday = new Date("January 1,2022");
            String id = new String("kakao");
            String address = new String("기본 주소를 설정해 주세요");
            String tel = new String("02-0000-0000");

            if (mapper.findByEmailKakao(email) != null) { //이메일이 존재하면 그냥 이메일로 찾은 사용자정보 돌려준다
                System.out.println("사용자 정보 있음 :"+email);
                //httpSession.setAttribute("kakao", new Kakao(kakao));

                //AuthInfo 세션에 저장하기

                Member member = memberRegisterService.selectByEmailOnly(kakao.getEmail());
                AuthInfo authInfo = new AuthInfo(member.getMemberId(), member.getMemberName(), member.getMemberNum(),member.getMemberPw(), kakao.getEmail(), kakao.getPicture(),member.getMemberType());
                httpSession.setAttribute("authInfo", authInfo);
                return userInfo;

            } else {
                Member newMember = new Member(0, id, pw, nickname, bday, address,
                        tel, ph, email, 0, 0);
                Member newMemberInfo = new Member(0, 1); //회원타입 일반회원:1
                mapper.insertMember(newMember);
                mapper.insertMemberInfo(newMemberInfo);
                mapper.saveKakao(kakao);
                //httpSession.setAttribute("kakao", new Kakao(kakao));

                //AuthInfo 세션에 저장하기

                Member member = memberRegisterService.selectByEmailOnly(kakao.getEmail());
                AuthInfo authInfo = new AuthInfo(member.getMemberId(), member.getMemberName(), member.getMemberNum(),member.getMemberPw(), kakao.getEmail(), kakao.getPicture(),member.getMemberType());
                httpSession.setAttribute("authInfo", authInfo);

                System.out.println("카카오 가입완료/ 세션 저장 완료");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}