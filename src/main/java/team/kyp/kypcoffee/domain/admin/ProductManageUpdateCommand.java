package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductManageUpdateCommand {
    private int productCode;
    private int productType;
    private String productName;
    private int productPrice;
    private MultipartFile productImg;
    private MultipartFile productContentImg;
    private String imgName;
    private String contentImgName;
}