package team.kyp.kypcoffee.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.exception.AlreadyExistingMemberException;
import team.kyp.kypcoffee.service.MemberRegisterService;
import team.kyp.kypcoffee.validator.RegisterRequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberRegisterService memberRegisterService;


    //////////////////////////////////////////////////////////////////////////////비밀번호 찾기

    @GetMapping("/member/findPass") //새창 띄우기
    public String findPass(Model model) {
        return "member/findId";
    }

    @GetMapping("/member/findPw/{id}") //새창 띄우기
    public String findPw(Model model,@PathVariable("id") String id) {

        Member member = memberRegisterService.selectByIdAll(id);
        model.addAttribute("member",member);
        return "member/setPw";
    }

    @RequestMapping("/member/findPw")
    @ResponseBody
    public HashMap<String, Object> setPw(Model model, @RequestBody RegisterRequest regReq) {
        String memberPw=regReq.getPw();
        String memberId=regReq.getId();

        memberRegisterService.updatePw(regReq);

        System.out.print("비밀번호 변경 성공");

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("valid",memberId);
        System.out.print(map);

        return map;
    }


    @PostMapping("/member/findId")
    @ResponseBody
    public HashMap<String, Object> findId(Model model, @RequestBody RegisterRequest regReq,
                                            HttpServletRequest request) {

        Integer valid;
        String memberId=regReq.getId(); //제이슨에서 받아온 정보=입력한 아이디
        String memberPhone=regReq.getPhone();

        Member member= memberRegisterService.selectByIdAll(memberId);
        String id = member.getMemberId();
        String ph = member.getMemberPhone();


            if(memberId.equals(id) && memberPhone.equals(ph)){
                System.out.print("회원정보가 일치합니다");
                valid=1;

            } else if(!memberId.equals(id)|| !memberPhone.equals(ph)) {
                System.out.print("회원정보가 일치하지 않습니다.");
                valid=0;
            } else {
            System.out.print("회원정보 존재하지않음");
            valid=2;
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("valid",valid); // 핸드폰번호와 아이디가 일치하는지 전송
        System.out.print(map);

        return map;
    }

    ////////////////////////////////////////////////////////////////////////////관리자기능 - 회원목록
    @GetMapping("/admin/memberManage")
    public String selectAllMember(Model model,
                                  @RequestParam(value = "section", defaultValue = "1") int section,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        int totalCnt = memberRegisterService.pagingCount();
        Paging paging = new Paging(section, pageNum);

        List<Member> list = memberRegisterService.selectMemberListPaging(paging);
        String totalCntJudge = memberRegisterService.totalCntJudge(totalCnt);

        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("memberList", list);

        return "member/memberList";
    }

    @GetMapping("/member/view/{memberNum}")
    public String selectMemberView(Model model,@PathVariable("memberNum") int memberNum) {
        Member member = memberRegisterService.selectMemberInfoByNum(memberNum);
        model.addAttribute("view",member);

        return "member/memberView";
    }

    @GetMapping("/member/edit/{memberNum}")
    public String editMemberInfo(Model model,@PathVariable("memberNum") int memberNum) {
        Member member = memberRegisterService.selectByMnum(memberNum);
        model.addAttribute("member",member);

        return "member/updateByAdmin";
    }

    @RequestMapping(value="/updateInfoByAdmin", method= RequestMethod.POST) //폼에서 받아와서 회원정보수정
    public String updateInfoByAdmin(RegisterRequest regReq, Model model, HttpSession session,
                                    @RequestParam(value = "section", defaultValue = "1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        memberRegisterService.updateMemberByAdmin(regReq);
        System.out.println("세션저장 / 회원정보수정 완료");

        int totalCnt = memberRegisterService.pagingCount();
        Paging paging = new Paging(section, pageNum);

        List<Member> list = memberRegisterService.selectMemberListPaging(paging);
        String totalCntJudge = memberRegisterService.totalCntJudge(totalCnt);

        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("memberList", list);

        return "member/memberList";

    }

    @GetMapping("/member/delete/{memberNum}") //관리자가 회원정보 삭제
    public String deleteMemberInfo(Model model,@PathVariable("memberNum") int memberNum,
                                   @RequestParam(value = "section", defaultValue = "1") int section,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        memberRegisterService.delete(memberNum);

        int totalCnt = memberRegisterService.pagingCount();
        Paging paging = new Paging(section, pageNum);

        List<Member> list = memberRegisterService.selectMemberListPaging(paging);
        String totalCntJudge = memberRegisterService.totalCntJudge(totalCnt);

        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("memberList", list);

        return "member/memberList";
    }

    /////회원가입기능////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/register/agreement") //약관동의 폼으로 이동
    public String agreement1(Model model) {
        return "register/agreement";
    }

    @GetMapping("/register/selection")
    public String getselection(Model model) {
        return "register/selection";
    }


    @RequestMapping(value="/register/agreement2", method = RequestMethod.GET)//약관동의시 회원가입 선택 폼으로 이동
    public String agreement2(@RequestParam(value="agree",defaultValue="false", required = false) Boolean agree, Model model,
                             HttpSession session) {

        if(!agree) {
            return "register/agreement"; //동의안하면 약관폼에 그대로 있기
        }

            return "register/selection";
    }

    @GetMapping("/register/register")
    public String selection(Model model) {
        model.addAttribute("registerForm",new RegisterRequest());  //작성폼 받아오기
        return "register/register";
    }

    @GetMapping("/register/success")
    public String success(Model model) {
        return "register/success";
    }

    @RequestMapping("/register/validateId")
    @ResponseBody
    public HashMap<String, Object> validate(Model model, @RequestBody RegisterRequest regReq,
                                 HttpServletRequest request) {

        String memberId=regReq.getId(); //제이슨에서 받아온 정보=입력한 아이디

        Member member= memberRegisterService.selectById(memberId);

        String valid = member.getMemberId();

        if(!valid.equals("1")){
            System.out.print("아이디 중복");
        }else if(valid.equals("1")){
            System.out.print("아이디 사용 가능");
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("valid",valid); // DB에 존재하는 아이디인지?
        System.out.print(map);


        return map;
    }

    @RequestMapping("/register/validateEmail")
    @ResponseBody
    public HashMap<String, Object> validateEmail(Model model, @RequestBody RegisterRequest regReq,
                                            HttpServletRequest request) {

        String memberEmail=regReq.getEmail(); //제이슨에서 받아온 정보=입력한 이메일

        Member member= memberRegisterService.selectByEmail(memberEmail); //이메일 여부만 0,1로 뽑아올때

        String validEmail = member.getMemberEmail();

        if(!validEmail.equals("1")){
            System.out.print("이메일 중복");
        }else if(validEmail.equals("1")){
            System.out.print("이메일 사용 가능");
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("validEmail",validEmail); // DB에 존재하는 아이디인지?
        System.out.print(map);


        return map;
    }

    @RequestMapping(value="/register/register", method= RequestMethod.POST) //회원가입 실행-db전송
    public String register(RegisterRequest regReq, Errors errors,Model model, HttpSession session) {

        System.out.println("폼 정보받아오기 테스트"+ regReq.getName() + +regReq.getEmailyn()+ regReq.getType());

        new RegisterRequestValidator().validate(regReq, errors);

        if(errors.hasErrors()) {
            return "register/register";
        }

        try {
            memberRegisterService.register(regReq);
            System.out.println("세션저장/회원가입 완료");

            return "register/success";

        }catch(AlreadyExistingMemberException e) {
            //errors.rejectValue("email", "duplicate"); //메세지 수정해야함
            return "register/register";
        }


    }

    @RequestMapping(value="/updateInfo", method= RequestMethod.GET) //회원정보수정
    public String update(Model model,
                         HttpSession session, Member member) {

        model.addAttribute("updateForm",new RegisterRequest());

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        if(ai.getId().equals("google") || ai.getId().equals("kakao")){
            member = memberRegisterService.selectByMnum(ai.getNo());//정보수정할 회원정보 보여주기
            model.addAttribute("member", member);
            return "member/updateMember";
        }

        if(ai!=null){
            member = memberRegisterService.selectByMnum(ai.getNo());//정보수정할 회원정보 보여주기
            model.addAttribute("member", member);
        }

        return "member/update";
    }

    @RequestMapping(value="/updateInfo", method= RequestMethod.POST) //폼에서 받아와서 회원정보수정
    public String updateInfo(RegisterRequest regReq, Model model, HttpSession session) {

        memberRegisterService.update(regReq);
        System.out.println("세션저장 / 회원정보수정 완료");
        return "mypage";

    }

   @RequestMapping(value="/updateInfoMember", method= RequestMethod.POST) //폼에서 받아와서 회원정보수정
    public String updateInfoMember(RegisterRequest regReq, Model model, Errors errors, HttpSession session) {

        if(errors.hasErrors()) {
            return "mypage";
        }
        memberRegisterService.updateMember(regReq);
        System.out.println("세션저장 / 회원정보수정 완료");

        return "mypage";

    }

    @GetMapping("/unregister") //다시묻기로 이동
    public String unregister(Model model) {
        return "member/unregister";
    }

    @GetMapping("/unregister2") //회원탈퇴진행
    public String unregister2(Model model,HttpSession session) {

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        memberRegisterService.delete(ai.getNo());
        session.invalidate(); //세션에 저장된 모든 데이터를 제거

        return "member/unregisterSuccess";
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////사업자회원가입
    @GetMapping("/register/businessAuthForm")
    public String business(Model model) {
        return "register/businessAuth"; //사업자 인증폼으로 이동
    }

    @GetMapping("/register/businessRegister")
    public String businessregi(Model model) {
        model.addAttribute("businessForm",new RegisterRequest());  //작성폼 받아오기

        return "register/business"; //회원가입폼으로 이동
    }

    @RequestMapping(value="/register/businessAuth", method= RequestMethod.POST) //회원가입폼 전송받음. -db전송
    public String businessregister(RegisterRequest regReq, Errors errors, Model model, HttpSession session) {

        System.out.println("폼 정보받아오기 테스트"+ regReq.getName() + +regReq.getEmailyn());

        new RegisterRequestValidator().validate(regReq, errors);

        if(errors.hasErrors()) {
            return "register/business";
        }

        try {
            memberRegisterService.register(regReq);
            System.out.println("세션저장/회원가입 완료");

            return "register/success";

        }catch(AlreadyExistingMemberException e) {
            //errors.rejectValue("email", "duplicate"); //메세지 수정해야함
            return "register/register";
        }


    }


}
