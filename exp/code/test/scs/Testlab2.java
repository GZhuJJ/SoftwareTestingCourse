package scs;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Testlab2 {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	  public static List<List<String>> allData=new ArrayList<List<String>>();

	 //读取excel表
	  public List<List<String>> read( ) throws IOException {  
		     try {  
		       
		            File excelFile = new File("src/resources/driver/软件测试名单.xlsx"); 
		            FileInputStream is = new FileInputStream(excelFile);  
		            XSSFWorkbook workbook = new XSSFWorkbook(is);   
		            XSSFSheet sheet = workbook.getSheetAt(0);    
 
		            int count = 0;  
		            for (Row row : sheet) {  
  
		                if(count == 0||count==1){  
		                    count++;  
		                    continue;  
		                }  
		                
		                if(row.getCell(0).toString().equals("")){  
		                    break ;  
		                }  
		                List<String> Data = new ArrayList<String>(); 
		                for (Cell cell : row) {  
		                    if(cell.toString() == null){  
		                        continue;  
		                    }  
		                    int cellType = cell.getCellType();  
		                    String cellValue = "";  
		                    switch (cellType) {  
	                        case Cell.CELL_TYPE_STRING:      
	                            cellValue = cell.getRichStringCellValue().getString(); 
	                            break;  
	                        case Cell.CELL_TYPE_NUMERIC:    
	                                cell.setCellType(Cell.CELL_TYPE_STRING);  
	                                cellValue = String.valueOf(cell.getRichStringCellValue().getString());  
	                            
	                            break;  
		                    }
		                    Data.add(cellValue);
		            }   
		                allData.add(Data);
		            }   
		     }catch (Exception e) {  
		            e.printStackTrace();  
		        } finally{    
		        }
			return allData;  
		    } 
	
	  @Before
	  public void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		  System.setProperty("webdriver.gecko.driver", driverPath);
		  driver = new FirefoxDriver();
		  baseUrl = "http://121.193.130.195:8800";
		  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		  
	  }

	@Test
	public void test() throws IOException {
		Testlab2 la=new Testlab2();
		la.read();
		driver.get(baseUrl+"/");
		for(int i=0;i<allData.size();i++) {	
//		WebElement we=driver.findElement(By.name("id"));
//		WebElement we1=driver.findElement(By.name("password"));
//		we.clear();
//		we1.clear();
		driver.findElement(By.name("id")).click();
		driver.findElement(By.name("id")).clear();
		driver.findElement(By.name("id")).sendKeys(allData.get(i).get(1));
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(allData.get(i).get(1).substring(4));
		driver.findElement(By.id("btn_login")).click();
		assertEquals(allData.get(i).get(3) ,driver.findElement(By.id("student-git")).getText());
		driver.findElement(By.id("btn_logout")).click();
		driver.findElement(By.id("btn_return")).click();
	    }
		  
	
	}
	
	@After
	  public void tearDown() throws Exception {
	    driver.quit();
	    
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
