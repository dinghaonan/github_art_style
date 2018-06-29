package com.neon;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class OperateExcelByPoi {

	public static void main(final String[] args) throws IOException {
		HSSFWorkbook wb = null;
		wb = new HSSFWorkbook();
		final HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth(13);
		final HSSFCellStyle style = wb.createCellStyle(); // 样式对象 
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平 

		final HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		style.setFont(font);

		// // 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));

		final Row row1 = sheet.createRow(0);

		final Cell cell1 = row1.createCell(0);
		cell1.setCellValue("序号");
		cell1.setCellStyle(style);

		final Cell cell2 = row1.createCell(1);
		cell2.setCellValue("检查项名称");
		cell2.setCellStyle(style);

		final Cell cell3 = row1.createCell(2);
		cell3.setCellValue("检查内容");
		cell3.setCellStyle(style);

		final Cell cell4 = row1.createCell(3);
		cell4.setCellValue("基本要求");
		cell4.setCellStyle(style);

		final Cell cell5 = row1.createCell(4);
		cell5.setCellValue("分值");
		cell5.setCellStyle(style);

		// -----------第二行-------------

		final Row row2 = sheet.createRow(1);

		// 子任务合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 9));
		// 设置子任务标题
		final Cell cell6 = row1.createCell(5);
		cell6.setCellValue("自查");
		cell6.setCellStyle(style);

		final Cell r2Cell1 = row2.createCell(5);
		r2Cell1.setCellValue("自查附件");
		r2Cell1.setCellStyle(style);

		final Cell r2Cell2 = row2.createCell(6);
		r2Cell2.setCellValue("自查记录");
		r2Cell2.setCellStyle(style);

		final Cell r2Cell3 = row2.createCell(7);
		r2Cell3.setCellValue("自查得分");
		r2Cell3.setCellStyle(style);

		final Cell r2Cell4 = row2.createCell(8);
		r2Cell4.setCellValue("加分项得分");
		r2Cell4.setCellStyle(style);

		final Cell r2Cell5 = row2.createCell(9);
		r2Cell5.setCellValue("适用情况");
		r2Cell5.setCellStyle(style);

		final FileOutputStream fileOut = new FileOutputStream("D:\\test.xls");
		wb.write(fileOut);
		fileOut.close();
	}
}