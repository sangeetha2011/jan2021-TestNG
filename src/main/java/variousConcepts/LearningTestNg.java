package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningTestNg {
	WebDriver driver;
   String browser = null;
   String url;
   @BeforeClass
   public void readconfig() {
	   //bufferReader//Inputstream//fileReader//Scanner
	   
	   
	   Properties prop = new Properties ();
	   try {
		   InputStream input = new FileInputStream ("./src/main/java/config/Config.properties");
		   prop.load(input);
		   browser = prop.getProperty("browser");
		   System.out.println("browser used:"+ browser);
		   url = prop.getProperty("url");
	   }catch(IOException e) {
		   e.printStackTrace();
	   }
	   
	   
	   
   }
   
  	@BeforeMethod
	public void init() {
    if( browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
    else if( browser.equalsIgnoreCase("Firefox"))  {
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");	
				//driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	public void input() throws InterruptedException {
	
		 Assert.assertEquals(driver.getTitle(), "Login - iBilling", "wrong page");
		  WebElement USERNAME_ID = driver.findElement(By.xpath("//input[@id='username']")); WebElement
		  PASSWORD_ID = driver.findElement(By.xpath("//input[@id='password']"));
		 WebElement LOGIN_ID =driver.findElement(By.
		 xpath("//button[@class='btn btn-success block full-width']"));
		  
		  String username="demo@techfios.com"; String password ="abc123";
		  
		 USERNAME_ID.sendKeys(username); PASSWORD_ID.sendKeys(password);
		 LOGIN_ID.click();
		 WebElement DASHBOARD_ID = driver.findElement(By.xpath(" //h2[contains(text(),' Dashboard ')]"));
		 //Assert.assertEquals(driver.getTitle(),"Dashboard- iBilling", "wrong page");
		 Assert.assertEquals(DASHBOARD_ID.getText(),"Dashboard", "wrong page");
		 

	}
}