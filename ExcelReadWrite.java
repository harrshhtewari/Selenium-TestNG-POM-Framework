/*package testcases;

import org.testng.annotations.Test;

import utils.ExcelUtility;

public class ExcelReadWrite 
{

    @Test(priority=1)
    public void testExcelReadWrite() 
    {
        
       ExcelUtility excel = new ExcelUtility();

        
        String FirstName = excel.getCellData("Sheet1", 1, 0);   // Reading data for HT
        String LastName = excel.getCellData("Sheet1", 1, 1);
        String eMail = excel.getCellData("Sheet1", 1, 2);
        String Gender = excel.getCellData("Sheet1", 1, 3);
        
        String FirstName2 = excel.getCellData("Sheet1", 2, 0); // Reading data For AG
        String LastName2 = excel.getCellData("Sheet1", 2, 1);
        String eMail2 = excel.getCellData("Sheet1", 2, 2);
        String Gender2 = excel.getCellData("Sheet1", 2, 3);
        
        String FirstName3 = excel.getCellData("Sheet1", 3, 0);   // Reading data For PS
        String LastName3 = excel.getCellData("Sheet1", 3, 1);
        String eMail3 = excel.getCellData("Sheet1", 3, 2);
        String Gender3 = excel.getCellData("Sheet1", 3, 3);
        
        String FirstName4 = excel.getCellData("Sheet1", 4, 0);
        String LastName4  = excel.getCellData("Sheet1", 4, 1);
        String eMail4     = excel.getCellData("Sheet1", 4, 2);
        String Gender4    = excel.getCellData("Sheet1", 4, 3);
        
        

        System.out.println("First Name : " + FirstName);  // O/p 1
        System.out.println("Last Name  : " + LastName);
        System.out.println("Email      : " + eMail);
        System.out.println("Gender     : " + Gender);
        
        
        System.out.println("First Name : " + FirstName2);  // O/p 2
        System.out.println("Last Name  : " + LastName2);
        System.out.println("Email      : " + eMail2);
        System.out.println("Gender     : " + Gender2);
        
        System.out.println("First Name : " + FirstName3);   // O/p 3
        System.out.println("Last Name  : " + LastName3);
        System.out.println("Email      : " + eMail3);
        System.out.println("Gender     : " + Gender3);
        
        System.out.println("First Name : " + FirstName4);   // O/p 3
        System.out.println("Last Name  : " + LastName4);
        System.out.println("Email      : " + eMail4);
        System.out.println("Gender     : " + Gender4);
    	
    	      
        

        // Write PASS in 4th column in the STATUS column 
        
        excel.setCellData("Sheet1", 1, 4, "PASS");
        excel.setCellData("Sheet1", 2, 4, "PASS");
        excel.setCellData("Sheet1", 3, 4, "PASS");
        excel.setCellData("Sheet1", 4, 4, "BSDK");
       
        System.out.println("PASS written successfully.");

        excel.closeWorkbook();
    }
}*/
/////////////////// The above is just reading and writing teh excel sheet but lengthy without using for loop//////

package testcases;

import org.testng.annotations.Test;
import utils.ExcelUtility;

public class ExcelReadWrite {

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






