/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeison.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author jvergara
 */
public class ReadExcelApachePoi {

    public static void main(String[] args) {
        try {
            XSSFRow row;
            XSSFCell cel;
           // InputStream inp = new FileInputStream("createworkbook.xlsx");
            XSSFWorkbook hSSFWorkbook = new XSSFWorkbook();
            OutputStream outputStream = new FileOutputStream(new File("createworkbook.xlsx"));
            XSSFSheet fSheet = hSSFWorkbook.createSheet("jeison vergara");
            int rowid = 0;
            int celid = 0;
            Map<Integer, String[]> data = new HashMap();
            data.put(1, new String[]{"emp id", "emp name", "emp desifnation"});
            data.put(2, new String[]{"01", "jeison a", "programer"});
            data.put(3, new String[]{"02", "yesenia hernandez", "programmer"});
            data.put(4, new String[]{"03", "andrews herrera", "dev ops"});
            Set<Integer> key = data.keySet();
            for (Integer integer : key) {
                row  = fSheet.createRow(rowid++);
                String[] dataObject = data.get(integer);
                for (String string : dataObject) {
                    cel = row.createCell(celid++);
                    cel.setCellValue(string);
                    
                }
                celid = 0;
            }
            hSSFWorkbook.write(outputStream);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
