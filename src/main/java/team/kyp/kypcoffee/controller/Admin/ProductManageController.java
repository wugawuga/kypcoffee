package team.kyp.kypcoffee.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.AdminProductRegiCommand;
import team.kyp.kypcoffee.domain.Paging;
import team.kyp.kypcoffee.domain.admin.ProductManage;
import team.kyp.kypcoffee.domain.admin.ProductManageUpdateCommand;
import team.kyp.kypcoffee.service.admin.AdminProductRegiService;
import team.kyp.kypcoffee.service.FileUploadService;
import team.kyp.kypcoffee.service.admin.ProductManageService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductManageController {

    private ProductManageService productManageService;
    private AdminProductRegiService adminProductRegiService;
    private FileUploadService fileUploadService;

    public ProductManageController(ProductManageService productManageService, AdminProductRegiService adminProductRegiService, FileUploadService fileUploadService) {
        this.productManageService = productManageService;
        this.adminProductRegiService = adminProductRegiService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/productManage")
    public String productManageList(@RequestParam(value = "section", defaultValue="1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int totalCnt = productManageService.selectAllCnt();
        List<ProductManage> productList = productManageService.selectProductList(new Paging(section, pageNum));
        String totalCntJudge = productManageService.totalCntJudge(totalCnt);

        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalCntJudge", totalCntJudge);
        model.addAttribute("section", section);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("productList", productList);
        return "admin/product/productManageList";
    }

    @GetMapping("/adminProductRegi")
    public String productRegiForm(AdminProductRegiCommand adminProductRegiCommand){

        return "admin/product/productRegi";
    }

    @PostMapping("/adminProductRegi")
    public String productRegi(AdminProductRegiCommand adminProductRegiCommand){

        //파일 업로드
        //adminProductRegiService.uploadProductImg(adminProductRegiCommand);

        String fileName = fileUploadService.uploadImg(adminProductRegiCommand.getProductImg());
        String contentFileName = fileUploadService.uploadImg(adminProductRegiCommand.getProductContentImg());
        adminProductRegiCommand.setImgName(fileName);
        adminProductRegiCommand.setContentImgName(contentFileName);

        //DB에 정보저장
        adminProductRegiService.adminProductRegi(adminProductRegiCommand);

        return "redirect:/admin/productManage";
    }

    @GetMapping("/productManage/detail/{productCode}")
    public String productDetail(@PathVariable("productCode") int productCode, Model model) {

        System.out.println("productCode : " + productCode);

        List<ProductManage> ProductManageDetail = productManageService.selectProductDetail(productCode);

        model.addAttribute("detail", ProductManageDetail.get(0));
        return "admin/product/productManageDetail";
    }

    @GetMapping("/productManage/delete/{productCode}")
    public String productDelete(@PathVariable("productCode") int productCode) {

        System.out.println("productCode : " + productCode);

        productManageService.selectProductDelete(productCode);

        return "redirect:/admin/productManage";
    }

    @GetMapping("/productManage/update/{productCode}")
    public String productUpdateForm(ProductManageUpdateCommand productManageUpdateCommand, @PathVariable("productCode") int productCode, Model model) {

        List<ProductManage> ProductManageDetail = productManageService.selectProductDetail(productCode);

        model.addAttribute("detail", ProductManageDetail.get(0));
        return "admin/product/productManageUpdate";
    }

    @PostMapping("/productManage/update")
    public String productUpdateDo(ProductManageUpdateCommand productManageUpdateCommand, Model model) {

        System.out.println("productManageUpdateCommand.getProductName() = " + productManageUpdateCommand.getProductName());
        System.out.println("productManageUpdateCommand.getProductImg() = " + productManageUpdateCommand.getProductImg());
        System.out.println("productManageUpdateCommand.getProductPrice() = " + productManageUpdateCommand.getProductPrice());
        System.out.println("productManageUpdateCommand.getProductType() = " + productManageUpdateCommand.getProductType());
        System.out.println("productManageUpdateCommand.getProductContentImg() = " + productManageUpdateCommand.getProductContentImg());

        String fileName = fileUploadService.uploadImg(productManageUpdateCommand.getProductImg());
        String contentFileName = fileUploadService.uploadImg(productManageUpdateCommand.getProductContentImg());
        productManageUpdateCommand.setImgName(fileName);
        productManageUpdateCommand.setContentImgName(contentFileName);

        //DB 수정
        productManageService.productUpdate(productManageUpdateCommand);

        return "redirect:/admin/productManage/detail/"+productManageUpdateCommand.getProductCode();
    }


}
