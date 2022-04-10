package team.kyp.kypcoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.*;
import team.kyp.kypcoffee.service.IamportService;
import team.kyp.kypcoffee.service.QnaBoardService;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final QnaBoardService qnaBoardService;
    private final IamportService iamportService;

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypageQna(@ModelAttribute("QnaBoard") QnaBoard qnaBoard, Model model, Errors errors, HttpSession session) {
        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        if(ai.getId().equals("admin")){
            return "admin/adminMain";
        }

        int memberNum = ai.getNo();

        List<QnaBoard> list = qnaBoardService.selectViewByNum(memberNum);
        model.addAttribute("boardList", list);

        List<Payment> payments = iamportService.selectPayment(memberNum);
        model.addAttribute("payment", payments);

        List<PayInfoCount> infoCounts = iamportService.selectPayCount(memberNum);
        model.addAttribute("payCount", infoCounts);

        return "mypage";
    }

    @GetMapping("/mypage/{qnaBoardNum}") //mypageView
    public String mypageView(@PathVariable("qnaBoardNum") int qnaBoardNum,
                             @ModelAttribute("QnaBoard") QnaBoard qnaBoard, Model model) {

        QnaBoard view = qnaBoardService.selectView(qnaBoardNum);
        model.addAttribute("view", view);

        List<Comment> cmt = qnaBoardService.cmtList(qnaBoardNum);
        model.addAttribute("cmt", cmt);

        return "mypageView";
    }
}


/*

    1.
    장바구니에서 체크 두개 이상해서 결제 했을 때
    디비 인서트 어떻게 할지??
    currval 써야함??? 왜????

    2.
    주문 날짜 Date String 오류
    주문 상품 번호 받아서 productName 가져오기

 */

























