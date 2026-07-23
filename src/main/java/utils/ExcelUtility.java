

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{

    private Workbook workbook;
    private Sheet sheet;
    private FileInputStream fis;
    private FileOutputStream fos;

    // Excel File Path
   // private String filePath = "C:\\Users\\htewari\\OneDrive - Kuwait Food Company\\Desktop\\PracticeFormData.xlsx";
    private String filePath =System.getProperty("user.dir")	+ "/src/test/resources/PracticeFormData.xlsx";


    // Constructor
 /*   public ExcelUtility()
    {

        try
        {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }*/
    
    public ExcelUtility() {

        try {

            File file = new File(filePath);

            System.out.println("Excel Path: " + file.getAbsolutePath());
            System.out.println("File Exists: " + file.exists());

            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);

        }
        catch (Exception e) 
        {

            System.out.println("Unable to load Excel file");
            e.printStackTrace();
        }
    }
    
      
    

    // Get Total Rows
    public int getRowCount(String sheetName)
    {

        sheet = workbook.getSheet(sheetName);

        return sheet.getLastRowNum();
    }

    // Get Total Columns
    public int getColumnCount(String sheetName)
    {

        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(0);

        return row.getLastCellNum();
    }

    // Read Cell Data
    public String getCellData(String sheetName, int rowNum, int colNum) 
    {

        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNum);

        Cell cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell);
    }

    // Write Cell Data
    public void setCellData(String sheetName, int rowNum, int colNum, String value) 
    {

        try 
        {

            sheet = workbook.getSheet(sheetName);

            Row row = sheet.getRow(rowNum);

            if (row == null) 
            {

                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);

            if (cell == null)
            {

                cell = row.createCell(colNum);
            }

            cell.setCellValue(value);

            fos = new FileOutputStream(filePath);

            workbook.write(fos);

            fos.close();

        } catch (Exception e) 
        {

            e.printStackTrace();
        }
    }

    // Close Workbook
    public void closeWorkbook()
    {

        try
        {

            if (workbook != null)
                workbook.close();

            if (fis != null)
                fis.close();

        } catch (IOException e)
        {

            e.printStackTrace();
        }
    }
}