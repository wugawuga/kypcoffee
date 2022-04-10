package team.kyp.kypcoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.service.ProductListServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductListController {

    private final ProductListServiceImpl productListServiceImpl;

    @GetMapping("product")
    public String productManageList(@RequestParam(value = "section", defaultValue="1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = productListServiceImpl.selectAllCnt();
        List<Product_info> productList = productListServiceImpl.selectProductList(new Paging(section, pageNum));
        String totalCntJudge = productListServiceImpl.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("all", productList);
        return "product/products";
    }

//    @GetMapping("/product/{productType}")
//    @ResponseBody
//    public List<Product_info> productType(@PathVariable("productType") int productType, Model model) {
//
//        List<Product_info> type = productListServiceImpl.findType(productType);
//
//        return type;
//    }

    @GetMapping("/product/{productType}")
    public String productType(@PathVariable("productType") int productType,
                              @RequestParam(value = "section", defaultValue="1") int section,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {

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

        return "product/productsType";
    }

    @GetMapping("/products/details")
    public String productDetail(@RequestParam(value = "pdtCode", required = false) Long productCode, HttpSession session, Model model) {

        if (productCode == null) {

            return "redirect:/product";
        }

        Product_info info = productListServiceImpl.detailByCode(productCode);

        model.addAttribute("info", info);

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");

        model.addAttribute("authInfo", ai);

        return "product/productsDetails";
    }
}
