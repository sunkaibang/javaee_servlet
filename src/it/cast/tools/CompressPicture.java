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
 * ͼƬѹ������ 
 * @author ����ǿ 
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
     * ���캯�� 
     */  
    public CompressPicture(String fileName,String servletPath) throws IOException {  
        file = new File(fileName);// �����ļ�  
        if(fileName.contains("\\"))  	//�õ��ļ�����
        	this.fileName=fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf("."));
        else if(fileName.contains("/"))
        	this.fileName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.lastIndexOf("."));
        if(servletPath.contains("/"))
        	servletPath=servletPath.replace("/", "\\");
        if(servletPath.charAt(servletPath.length()-1)!='\\')
        	servletPath=servletPath+"\\";
        this.servletPath=servletPath;
        this.servletPath=this.servletPath+CommonsUtils.uuid()+this.fileName+".png";	//���յı���·��
        img = ImageIO.read(file);      // ����Image����  
        width = img.getWidth(null);    // �õ�Դͼ��  
        height = img.getHeight(null);  // �õ�Դͼ��  
    }  
    /** 
     * ���տ�Ȼ��Ǹ߶Ƚ���ѹ�� 
     * @param w int ����� 
     * @param h int ���߶� 
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
     * ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С 
     * @param w int �¿�� 
     * @param h int �¸߶� 
     */  
    public void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ  
        File destFile = new File(servletPath);  
        FileOutputStream out = new FileOutputStream(destFile); // ������ļ���  
        // ��������ʵ��bmp��png��gifתjpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG����  
        file.delete();
        out.close();  
    }
    
}  
