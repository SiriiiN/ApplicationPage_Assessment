import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class JobApplication {
	
	WebDriver driver =null;
	@BeforeTest
	public void before() {
		
		// chrome driver instance to launch url pr website
		ChromeDriverManager.getInstance().setup();
		 driver=new ChromeDriver();
	
	}
//	@Test
	public void apply() {
		driver.get("https://www.hashtag-ca.com/careers/apply?jobCode=QAE001");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1700)", "");
		
		driver.findElement(By.name("name")).sendKeys("abcd xyz");
		driver.findElement(By.name("email")).sendKeys("abcdxyz@gmail.com");
		driver.findElement(By.name("phone")).sendKeys("7981141234");
		driver.findElement(By.name("notice_time")).sendKeys("90");
		driver.findElement(By.name("experience")).sendKeys("3");
		driver.findElement(By.name("resume")).sendKeys("C://Myfiles//ABCD Resume.docx");
		driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > textarea"))
		.sendKeys("Highly skilled automation tester with 3 years of experiencein designing,"
				+ " developing, and executing automated test scripts");
		driver.findElement(By.cssSelector("#contact-form > div > div.col-lg-10.col-md-12.col-sm-12.pt-5.pb-5.d-flex.justify-content-center > div > button.btn.form-button.font-12.font-bold"))
		.click();
		
		driver.quit();
	}
	

	

}
