package com.qa.util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.base.TestBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Iterator;

public class ExceltoFeatures extends TestBase {

    

    public static void GenerateFeatureFiles(String FILE_NAME) {
    	String data="";

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            for (int k = 0; k < workbook.getNumberOfSheets(); k++)
            {
            Sheet datatypeSheet = workbook.getSheetAt(k);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    
                    switch (currentCell.getCellType()) 
                    {
            	        case BOOLEAN:
            	            data=String.valueOf(currentCell.getBooleanCellValue());
            	            break;
            	        case STRING:
            	        	data=currentCell.getRichStringCellValue().getString();
            	            break;
            	        case NUMERIC:
            	            if (DateUtil.isCellDateFormatted(currentCell)) {
            	            	data=String.valueOf(currentCell.getDateCellValue());
            	            } else {
            	            	data=new BigDecimal(currentCell.getNumericCellValue()).toPlainString();
            	            }
            	            break;
            	        case FORMULA:
            	        	data =currentCell.getCellFormula();
            	            break;
            	        case BLANK:
            	        	data ="";
            	            break;
            	        default:
            	            System.out.print("");
            	    	}
                   System.out.println(data);
                   
                }
//                System.out.println();
                WriteToFile(data,System.getProperty("user.dir")+"\\src\\test\\resources\\features\\"+datatypeSheet.getSheetName()+".feature");
            }
            
            System.out.println("-------++++++++++++---------------");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
	public static void WriteToFile(String str, String filePath) throws IOException {
		BufferedWriter bw = null;
		try {
			 FileOutputStream out = new FileOutputStream(filePath, true); // true, means: file appended content, not regenerated, default is false
			bw = new BufferedWriter(new OutputStreamWriter(out, "GBK"));
			 bw.write(str += "\r\n");// Line break
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
	}
}