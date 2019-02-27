package it.cast.servlet;


import it.cast.domain.ManagerTeacher;
import it.cast.tools.CommonsUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class toExcelServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="+CommonsUtils.uuid()+".xls");
        //����ǰ̨��������
        String title=req.getParameter("title");
        int colNumber=Integer.parseInt(req.getParameter("colNumber"));
        String dataStr = req.getParameter("jsonData");
        String maker="";		//�Ʊ���
        Date time=new Date();	//�Ʊ�ʱ��
        DateFormat df=DateFormat.getDateInstance();//ʱ����ʽ
        ManagerTeacher mt=new ManagerTeacher();
        if(dataStr.contains("�༭")||dataStr.contains("ɾ��")){
        	colNumber=colNumber-2;
        	mt=(ManagerTeacher) req.getSession().getAttribute("session_manager");
        	if(mt!=null)
        		maker=mt.getId();
        }else{
        	mt=(ManagerTeacher) req.getSession().getAttribute("session_teacher");
        	if(mt!=null)
        		maker=mt.getId();
        }
        	
        dataStr = dataStr.replace("[{", "").replace("}]", "").replace("{", "");
        String[] dataArr = dataStr.split("},");
        boolean isPic=dataStr.contains("picture");
        
       
        try {
            //����һ��workbook(��ӦExcel�ļ� )
            WritableWorkbook workbook = Workbook.createWorkbook(resp.getOutputStream());
            //��workbook�����һ��sheet(Excel�ļ��е�sheet)
            WritableSheet sheet = workbook.createSheet("��������", 0);
            //������ͷ��Ԫ�񣬲��������ָ�ʽ
            WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setWrap(true);
            //��Ԫ����ʽ
            WritableCellFormat cellWCF=new WritableCellFormat(wf);
            cellWCF.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellWCF.setAlignment(Alignment.CENTRE);
            cellWCF.setWrap(true);
            //���ñ���
            //int colNumber=dataArr[0].split(",").length-1;
            sheet.mergeCells(0, 0, colNumber-1, 1);
            sheet.addCell(new Label(0,0,title,wcf));
            wcf = new WritableCellFormat(wf);		//���ⲻ��Ҫ�߽磬��ͷ��Ҫ
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN);	
            //��������
            //sheet.setRowView(1, 100);
            //��������
            int i=0;
            int j=0;
            if(isPic==false){
	            for (i = 0; i < dataArr.length; i++) {
	                //ÿһ�е�����
	               // System.out.println("data:"+dataArr[i]);
	                String[] str = dataArr[i].split(",");
	                
	                //ÿһ��
	                for (j = 0; j < colNumber; j++) {
	                    if(i==0){
	                        sheet.addCell(new Label(j, i+2, str[j], wcf));//new Label(�к�, �к�, ����, ��Ԫ��)
	                        continue;
	                    }
	                    sheet.addCell(new Label(j, i+2, str[j],cellWCF));
	                }
	                
	            }
            }else{
            	//����Ƭ
            	i=0;
            	j=0;
            	for (i = 0; i < dataArr.length; i++) {
	                //ÿһ�е�����
	                //System.out.println("data:"+dataArr[i]);
            		
	                String[] str = dataArr[i].split(",");
	                //ÿһ��
	                for (j = 0; j < colNumber-1; j++) {
	                    if(i==0){
	                        sheet.addCell(new Label(j, i+2, str[j], wcf));//new Label(�к�, �к�, ����, ��Ԫ��)
	                        continue;
	                    }
	                    sheet.addCell(new Label(j, i+2, str[j],cellWCF));
	                }
	                if(i==0)
	                	sheet.addCell(new Label(j, i+2, str[j],wcf));
	                else{
		                String filePath=str[j].substring(str[j].indexOf("UpLoad")+7, str[j].indexOf("alt")-2);
		                
		                if(!filePath.contains(".png")){
		                	filePath="����Ƭ";
		                	sheet.addCell(new Label(j,i+2,filePath,cellWCF));
		                }else{
		                	filePath=req.getContextPath()+"\\FileUpLoad\\"+filePath;
		                	System.out.println(filePath);
		                	WritableImage wrimage=new WritableImage(j,i+2,1,1,
		                			new File("E:\\TomcatInstall\\apache-tomcat-7.0.82\\webapps"+filePath));
//		                	WritableImage wrimage=new WritableImage(j,i+2,1,1,
//		                			new File("D:\\sunkaibang\\apache-tomcat-7.0.82\\webapps"+filePath));
			                sheet.addImage(wrimage); 
		                }
		                
	                }
	            }
            }
            //�Ʊ��˺��Ʊ�ʱ��
            cellWCF=new WritableCellFormat(wf);
            cellWCF.setAlignment(Alignment.CENTRE);
            cellWCF.setWrap(true);
        	i=i+4;
            sheet.mergeCells(j-1, i,j, i);
            sheet.addCell(new Label(j-1,i,"�Ʊ���:"+maker,cellWCF));
            i++;
            
            sheet.mergeCells(j-1, i,j, i);
            sheet.addCell(new Label(j-1,i,"ʱ��:"+df.format(time),cellWCF));
            //end
            workbook.write();
            workbook.close();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
