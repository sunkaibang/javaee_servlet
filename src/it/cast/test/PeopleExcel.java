package it.cast.test;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PeopleExcel {

	/**
	 * 
	 * @author smart *
	 */
	public static void main(String[] args) {
		// 准备设置excel工作表的标题
		String[] title = { "编号", "产品名称", "产品价格", "产品数量", "生产日期", "产地", "是否出口" };
		try {
			// 获得开始时间
			long start = System.currentTimeMillis();
			// 输出的excel的路径
			String filePath = "g:\\test.xls";
			// 创建Excel工作薄
			WritableWorkbook wwb;
			// 新建立一个jxl文件,即在C盘下生成test.xls
			OutputStream os = new FileOutputStream(filePath);
			wwb = Workbook.createWorkbook(os);
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet("学生表", 0);
			Label label;
			
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            //wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
            
            WritableCellFormat cellWCF=new WritableCellFormat(wf);
            cellWCF.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellWCF.setAlignment(Alignment.CENTRE);
            
            sheet.mergeCells(0, 0, 10, 1);
            sheet.addCell(new Label(0,0,"Xxx同学求职自荐信",wcf));
            
            sheet.mergeCells(0, 2, 1, 3);
            sheet.addCell(new Label(0,2,"学号",cellWCF));
            sheet.mergeCells(2, 2, 3, 3);
            sheet.addCell(new Label(2,2,"Xxx",cellWCF));
            
            sheet.mergeCells(4, 2, 5, 3);
            sheet.addCell(new Label(4,2,"姓名",cellWCF));
            sheet.mergeCells(6, 2, 7, 3);
            sheet.addCell(new Label(6,2,"Xxx",cellWCF));
            
            sheet.mergeCells(0, 4, 1, 5);
            sheet.addCell(new Label(0,4,"性别",cellWCF));
            sheet.mergeCells(2, 4, 3, 5);
            sheet.addCell(new Label(2,4,"xxx",cellWCF));
            
            sheet.mergeCells(4, 4, 5, 5);
            sheet.addCell(new Label(4,4,"身高",cellWCF));
            sheet.mergeCells(6, 4, 7, 5);
            sheet.addCell(new Label(6,4,"xx",cellWCF));
            
            sheet.mergeCells(0, 6, 1, 7);
            sheet.addCell(new Label(0,6,"家庭住址",cellWCF));
            sheet.mergeCells(2, 6, 7, 7);
            sheet.addCell(new Label(2,6,"xxxxxxxx",cellWCF));
            
            sheet.mergeCells(0, 8, 1, 9);
            sheet.addCell(new Label(0,8,"毕业学校",cellWCF));
            sheet.mergeCells(2, 8, 9, 9);
            sheet.addCell(new Label(2,8,"xxxxxxxx",cellWCF));
            
            sheet.mergeCells(0, 10, 1, 13);
            sheet.addCell(new Label(0,10,"学历简历",cellWCF));
            sheet.mergeCells(2, 10, 9, 13);
            sheet.addCell(new Label(2,10,"xxxxxxxx",cellWCF));
            
            
            
            sheet.mergeCells(0, 14, 1, 15);
            sheet.addCell(new Label(0,14,"求职意向",cellWCF));
            sheet.mergeCells(2, 14, 9, 15);
            sheet.addCell(new Label(2,14,"xxxxxxxx",cellWCF));
            
            sheet.mergeCells(0, 16, 1, 17);
            sheet.addCell(new Label(0,16,"联系方式",cellWCF));
            sheet.mergeCells(2, 16, 9, 17);
            sheet.addCell(new Label(2,16,"xxxxxxxx",cellWCF));
            
            sheet.mergeCells(8, 2, 9, 7);
            sheet.addCell(new Label(8,2,"照片",cellWCF));
            
            
            cellWCF=new WritableCellFormat(wf);
            cellWCF.setAlignment(Alignment.CENTRE);
            
            sheet.mergeCells(7, 19, 8, 19);
            sheet.addCell(new Label(7,19,"日期:xx",cellWCF));
            wwb.write(); 
            wwb.close(); 
		} catch (Exception e) {
			System.out.println("---出现异常---");
			e.printStackTrace();
		}
	}

}