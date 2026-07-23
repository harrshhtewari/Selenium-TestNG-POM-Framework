
/////////////////// The above is just reading and writing teh excel sheet but lengthy without using for loop//////

package testcases;

import org.testng.annotations.Test;
import utils.ExcelUtility;

public class ExcelReadWritetest {

    @Test(priority = 1)
    public void testExcelReadWrite() 
    {

        ExcelUtility excel = new ExcelUtility();

        // storing total number of filled rows in the sheet and storing it oijn i variable 
        int totalRows = excel.getRowCount("Sheet1");
        System.out.println("The Total Rowsincluding the Header = "+totalRows);// printing the i 

        // Looping 
        for (int i = 1; i <= totalRows; i++) // 'coz dont want to write in the header which is already written 
        {

            String firstName = excel.getCellData("Sheet1", i, 0);  // 4 rows in total and 4 columns for every record.
            String lastName  = excel.getCellData("Sheet1", i, 1);
            String email     = excel.getCellData("Sheet1", i, 2);
            String gender    = excel.getCellData("Sheet1", i, 3);

            System.out.println("-------------------------------------");
            
            System.out.println("Row Number : " + i);
            System.out.println("First Name : " + firstName);
            System.out.println("Last Name  : " + lastName);
            System.out.println("Email      : " + email);
            System.out.println("Gender     : " + gender);

            // Write PASS in Result column (this will be written by my script i.e. PAAS under RESULT
            
            excel.setCellData("Sheet1", i, 4, "PASS");
            
        }
        
        excel.setCellData("Sheet1", 4, 4, "BSDK");// TREAT to absel

        System.out.println("\n-------------------------------------\n");
        
        System.out.println("PASS written successfully for all rows.");
        
        

        excel.closeWorkbook();
    }
}






