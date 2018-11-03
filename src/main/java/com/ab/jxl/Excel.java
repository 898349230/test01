package com.ab.jxl;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {
	public static void main(String[] args) {
		readExcel("C:\\Users\\lzx-t050\\Desktop\\test01.xls");
	}
	
	private static void readExcel(String filePath){
		Workbook workBook = null;
		try {
			//InputStream is = new FileInputStream(filePath);
			File file = new File(filePath);
			workBook = Workbook.getWorkbook(file);
			Sheet sheet = workBook.getSheet(0);
			int columns = sheet.getColumns();
			int rows = sheet.getRows();
			System.out.println("行数 ： "+ rows + "列数 : " + columns);
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					Cell cell = sheet.getCell(j, i);
					System.out.print(cell.getContents()+"\t");
				}
				System.out.println();
			}
			
			WritableWorkbook writableWorkBook = Workbook.createWorkbook(
					new File("C:\\Users\\lzx-t050\\Desktop\\test2.xls"), workBook);
			WritableSheet writableSheet = writableWorkBook.getSheet(1);
			WritableCell writableCell = writableSheet.getWritableCell(0,0);
			if(writableCell.getType()==CellType.LABEL){
				Label label = (Label) writableCell;
				label.setString("新姓名");
			}
			
			writableWorkBook.write();
			writableWorkBook.close();
			
		} catch (BiffException | IOException | WriteException e) {
			e.printStackTrace();
		}finally{
			workBook.close();
		}
	}
	
	private static void writeExcel(String fileName){
		
		//WritableWorkbook writeWorkBook = Workbook.createWorkbook(new File(fileName), in); 
	}
}
