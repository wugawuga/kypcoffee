package team.kyp.kypcoffee.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.kyp.kypcoffee.domain.AdminProductRegiCommand;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Override
    public String uploadImg(MultipartFile multipartFile) {
        String fileName = null;

        UUID uuid = UUID.randomUUID();

        if(!multipartFile.isEmpty()){
            fileName = uuid + "_" + multipartFile.getOriginalFilename();
        }

        try{
            String os = System.getProperty("os.name").toLowerCase();
            String pathName = "";
            if (os.contains("win")) {
                pathName = "C:\\productImg";
            } else if (os.contains("mac")) {
                pathName = "/Users/kypcoffee/Downloads/kypProductImg";
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                pathName = "/home/ubuntu/kypcoffee/kypProductImg";
            }

            File folder = new File(pathName);
            if (!folder.exists()) folder.mkdirs();

            File destination = new File(pathName + File.separator + fileName);
            multipartFile.transferTo(destination);

        }catch (Exception e){
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public String uploadImgUrl(String src, int cnt) {

        String fileName = "insta"+cnt+".jpg";

        String osName = System.getProperty("os.name").toLowerCase();
        String pathName = "";
        if (osName.contains("win")) {
            pathName = "C:\\reviewImg";
        } else if (osName.contains("mac")) {
            pathName = "/Users/kypcoffee/Downloads/kypReviewImg";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            pathName = "/home/ubuntu/kypcoffee/kypReviewImg";
        }

        try{
            URL url = new URL(src);
            //읽기 객체
            InputStream is = url.openStream();
            //쓰기 객체
            OutputStream os = new FileOutputStream(pathName + File.separator + fileName);

            byte[] buffer = new byte[1024*8];

            while (true) {
                int inputData = is.read(buffer);
                if(inputData == -1)break;
                os.write(buffer,0,inputData);
            }
            is.close(); os.close();
            System.out.println("웹에서 복사 성공함");
        }
        catch (Exception e){
            System.out.println("실패");
            e.printStackTrace();
        }
        return fileName;
    }
}
