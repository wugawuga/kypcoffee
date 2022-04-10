package team.kyp.kypcoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.service.ProductListServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductListServiceImpl productListServiceImpl;

    @GetMapping("/")
    public String main() {

        return "index";
    }

    @GetMapping("/intro")
    public String intro() {

        return "intro";
    }

    @GetMapping("/businessStore")
    public String businessStore(@RequestParam(value = "section", defaultValue="1") int section,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model, HttpSession session) {

        int totalCnt = productListServiceImpl.selectAllCnt();
        List<Product_info> productList = productListServiceImpl.selectProductList(new Paging(section, pageNum));
        String totalCntJudge = productListServiceImpl.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("all", productList);

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        if(ai==null){
            return "accessFailBusiness";
        }
        else if(ai.getType()==1) { //사업자 아니면 이용 불가
            return "accessFailBusiness";
        }

        return "business/productsBusiness";
    }

    @GetMapping("/business/product/{productType}")
    public String productType(@PathVariable("productType") int productType,
                              @RequestParam(value = "section", defaultValue="1") int section,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, HttpSession session , Model model) {

        int totalCnt = productListServiceImpl.selectAllCntByNum(productType);
        List<Product_info> productList = productListServiceImpl.selectProductListByNum(new Paging(section, pageNum, productType));
        String totalCntJudge = productListServiceImpl.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("type", productList);

        int productTypeInt = productList.get(0).getProductType();

        model.addAttribute("num", productTypeInt);

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        if(ai==null){
            return "accessFailBusiness";
        }
        else if(ai.getType()==1) { //사업자 아니면 이용 불가
            return "accessFailBusiness";
        }

        return "business/productsTypeBusiness";
    }

    @GetMapping("/business/products/details")
    public String productDetail(@RequestParam(value = "pdtCode", required = false) Long productCode, HttpSession session, Model model) {

        if (productCode == null) {

            return "redirect:/product";
        }

        Product_info info = productListServiceImpl.detailByCode(productCode);

        model.addAttribute("info", info);

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        model.addAttribute("authInfo", ai);

        if(ai==null){
            return "accessFailBusiness";
        }
        else if(ai.getType()==1) { //사업자 아니면 이용 불가
            return "accessFailBusiness";
        }

        return "business/productsDetailsBusiness";
    }

    @GetMapping("accessError")
    public String accessAdminUrl() {

        return "accessFailAdmin";
    }

}
