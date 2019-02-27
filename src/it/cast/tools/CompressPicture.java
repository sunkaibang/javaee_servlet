package it.cast.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/** 
 * 图片压缩处理 
 * @author 崔素强 
 */  
public class CompressPicture {  
	

    private Image img;  
    private int width;  
    private int height;  
    private String fileName;
    private String servletPath;
    private File file;
     
    public String getServletPath() {
		return servletPath;
	}
	/** 
     * 构造函数 
     */  
    public CompressPicture(String fileName,String servletPath) throws IOException {  
        file = new File(fileName);// 读入文件  
        if(fileName.contains("\\"))  	//得到文件名称
        	this.fileName=fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf("."));
        else if(fileName.contains("/"))
        	this.fileName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.lastIndexOf("."));
        if(servletPath.contains("/"))
        	servletPath=servletPath.replace("/", "\\");
        if(servletPath.charAt(servletPath.length()-1)!='\\')
        	servletPath=servletPath+"\\";
        this.servletPath=servletPath;
        this.servletPath=this.servletPath+CommonsUtils.uuid()+this.fileName+".png";	//最终的保存路径
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h) throws IOException { 
    	if(width>height){
    		if(width>w){
    			int newWidth=w;
    			int newHeight=(height * w) / width;
    			resize(newWidth,newHeight);
    		}else{
    			resize(this.width,this.height);
    		}
    	}else if(width<height){
    		if(height>h){
    			int newHeight=h;
    			int newWidth=(width * h) / height;
    			resize(newWidth,newHeight);
    		}else{
    			resize(this.width,this.height);
    		}
    	}else{
    		if(width>w){
    			resize(w,h);
    		}else{
    			resize(this.width,this.height);
    		}
    	}
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(servletPath);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        file.delete();
        out.close();  
    }
    
}  
