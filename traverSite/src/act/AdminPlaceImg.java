package act;

import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


public class AdminPlaceImg {
    public String[] saveImg(Collection<Part> collection) throws IOException {
        String imgs = "";
        for (Part part : collection) {
           
           if (part.getName().contains("img_file")) { // �̹����� ����ִ� input type=file �� �̸� ����
              String contentDisposition = part.getHeader("content-disposition");
              System.out.println(contentDisposition); //�� �޾ҳ� Ȯ��

              // file ��ü�� ������� form-data; name="file1"; filename="" �� ��µ�
              // ��) form-data; name="file1"; filename="���ε������ϸ�.Ȯ����"
              String uploadFileName = getUploadFileName(contentDisposition);
              if (!uploadFileName.equals("")) {// ���ε��� ������ ������
                 part.write(uploadFileName);
                 imgs += "," + uploadFileName ;
              }
           }
        }
        imgs = imgs.substring(1);
        String[] imgArr = imgs.split(",");
        
        return imgArr;
     }

     private String getUploadFileName(String contentDisposition) {
        String uploadFileName = null;
        String[] contentSplitStr = contentDisposition.split(";");
        
        int fIdx = contentSplitStr[2].indexOf("\"");
        int sIdx = contentSplitStr[2].lastIndexOf("\"");
        
        uploadFileName = contentSplitStr[2].substring(fIdx + 1, sIdx);
        
        return uploadFileName;
     }

}
