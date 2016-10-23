package cjc.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileUtil {
	public static  void upload(String filePath, MultipartFile file){
		try {
			  FileOutputStream  os = new FileOutputStream(filePath);
		     //拿到上传文件的输入流  
	         FileInputStream in = (FileInputStream) file.getInputStream();  
	         //以写字节的方式写文件  
	         int b = 0;  
	         while((b=in.read()) != -1){  
	             os.write(b);  
	         }  
	         os.flush();  
	         os.close();  
	         in.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}  
    
	}
       
   
     // 图片处理   
     public static String uploadCompressPic( FileInputStream in,String outputPath,boolean proportion,FileSie size) {  
         try {   
        	 
        	 int outputWidth = 2000; // 默认输出小图片宽  
     	      int outputHeight = 2000; // 默认输小图片高 
     	      if(size==FileSie.middle){//
     	    	 outputWidth=4000;
     	    	 outputHeight=4000;
     	      }
     	      if(size==FileSie.big){
     	    	 outputWidth=8000;
     	    	 outputHeight=8000;
     	      }
             Image img = ImageIO.read(in);   
             // 判断图片格式是否正确   
             if (img.getWidth(null) == -1) {  
                 return "no";   
             } else {   
                 int newWidth; int newHeight;   
                 // 判断是否是等比缩放   
                 if (proportion == true) {   
                     // 为等比缩放计算输出的图片宽度及高度   
                     double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
                     double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
                     // 根据缩放比率大的进行缩放控制   
                     double rate = rate1 > rate2 ? rate1 : rate2;   
                     newWidth = (int) (((double) img.getWidth(null)) / rate);   
                     newHeight = (int) (((double) img.getHeight(null)) / rate);   
                 } else {   
                     newWidth = outputWidth; // 输出的图片宽度   
                     newHeight = outputHeight; // 输出的图片高度   
                 }   
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
                  
                /* 
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
                 * 优先级比速度高 生成的图片质量比较好 但速度慢 
                 */   
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
                FileOutputStream out = new FileOutputStream(outputPath);  
                // JPEGImageEncoder可适用于其他图片类型的转换   
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
                encoder.encode(tag);   
                out.close();   
             }   
         } catch (IOException ex) {   
             ex.printStackTrace();   
         }   
         return "ok";   
    }   
     
     public enum FileSie{
    	 big,
    	 middle,
    	 small;
     }
}
