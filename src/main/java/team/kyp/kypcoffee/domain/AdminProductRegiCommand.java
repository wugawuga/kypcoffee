package team.kyp.kypcoffee.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AdminProductRegiCommand {
    private int productType;
    private String productName;
    private int productQuantity;
    private int productPrice;
    private MultipartFile productImg;
    private MultipartFile productContentImg;
    private String imgName;
    private String contentImgName;

}