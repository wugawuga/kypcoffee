package team.kyp.kypcoffee.service;

import org.springframework.web.multipart.MultipartFile;
import team.kyp.kypcoffee.domain.AdminProductRegiCommand;

public interface FileUploadService {

    public String uploadImg(MultipartFile multipartFile);

    public String uploadImgUrl(String src, int cnt);

}
