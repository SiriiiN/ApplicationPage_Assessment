package com.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.data.provider.DataProviderClass;
import com.pages.CareersPage;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class ApplicationNew {
	WebDriver driver;
	@BeforeTest
	public void  before() {
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.get("https://www.hashtag-ca.com/careers/apply?jobCode=QAE001");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1700)", "");
	}

	@Test(dataProvider="Input Data", dataProviderClass=DataProviderClass.class, invocationCount=1, threadPoolSize=3)
	public void applyWithDataProvider(String name,String email, String phone, String noticePeriod,String experience,String resume,
			String description) throws InterruptedException {
	//	CareersPage page= PageFactory.initElements(driver, CareersPage.class);

		//Actions at = new Actions(driver);
	//	page.enterName(name);
	
	   WebElement nameValue=	driver.findElement(By.name("name"));
	 nameValue.sendKeys(name);
	 String s1=  nameValue.getAttribute("value");
	   

	//	page.enterEmail(email);
	   WebElement emailValue= driver.findElement(By.name("email"));
	   emailValue.sendKeys(email);
	   String s2 = emailValue.getAttribute("value");

	//	page.enterPhone(phone);
	   WebElement phoneValue= driver.findElement(By.name("phone"));
	   phoneValue.sendKeys(phone);
		String inputphn=driver.findElement(By.name("phone")).getAttribute("value");
		System.out.println(inputphn);
		int phnLength=inputphn.length();

	//	page.enterNoticePeriod(noticePeriod);
		  WebElement npValue= driver.findElement(By.name("notice_time"));
		   npValue.sendKeys(noticePeriod);
		String inputNP=driver.findElement(By.name("notice_time")).getAttribute("value");
		System.out.println(inputNP);
		int npLength=inputNP.length();

	//	page.enterExperience(experience);
		  WebElement experienceValue= driver.findElement(By.name("experience"));
		  experienceValue.sendKeys(experience);
		String inputExp=driver.findElement(By.name("experience")).getAttribute("value");
		System.out.println(inputExp);
		int expLength=inputExp.length();

	//	page.uploadResume(resume);
		  WebElement resumeValue= driver.findElement(By.name("resume"));
		   resumeValue.sendKeys(resume);
		//	String fileName = uploadResume.getFileName();
		   String s6=resumeValue.getAttribute("value");


	//	page.enterDesciption(description);
		   WebElement descriptionValue= driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > textarea"));
		   descriptionValue.sendKeys(description);
		Thread.sleep(1000);

		
	//	driver.findElement(By.name("resume")).sendKeys("C://Myfiles//ABCD Resume.docx");
	 WebElement applyValue=	driver.findElement(By.cssSelector("#contact-form > div > div.col-lg-10.col-md-12.col-sm-12.pt-5.pb-5.d-flex.justify-content-center > div > button.btn.form-button.font-12.font-bold"));
		applyValue.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
	//	Thread.sleep(1000);
		nameValue.clear();
		emailValue.clear();
		phoneValue.clear();
		npValue.clear();
		experienceValue.clear();
		resumeValue.clear();
		descriptionValue.clear();
		
		//validation for name
	
		if(!name.matches("[a-zA-Z]+")) {
			String expected="Only alphabets, space, quote(') and dot(.) is supported.";
			Thread.sleep(1000);
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(2) > p")).getText();
			System.out.println("Expected error for name field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}else if (name.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(2) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		nameValue.clear();
		
		//validation for email
		
		if(!email.matches("[a-zA-Z+_.-]@[a-zA-Z][.com]")) {
			System.out.println("I'm inside email validation");
			String expected="Enter a valid email address.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(3) > p")).getText();
			System.out.println("Expected error for email field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}else if (email.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(3) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		emailValue.clear();
		
		//validation for phone
		
		if(phnLength==0) {
			System.out.println("Alphaets or special characters are not allowed for phone number.");
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(4) > p")).getText();
			System.out.println("Expected error for phone field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);

		} 
		else if (inputphn.matches(".+eE") ) {
			//LengthCheck =true;
			System.out.println("Only numbes are allowed for phone number.");
			String expected="something went wrong! please try again later";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > p:nth-child(5)")).getText();
			System.out.println("Expected error for phone field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(expected, actual);

		}else if(phnLength <0) {
			System.out.println("Invalid Phone number. Phone number must have 10 digits.");
		}else if (phone.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(4) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		phoneValue.clear();
		
		// validation for notice period
		
		if(npLength==0) {
			System.out.println("Alphaets or special characters are not allowed for nptice period.");
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(5) > p")).getText();
			System.out.println("Expected error for notice period field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);

		} else if (noticePeriod.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(5) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		npValue.clear();
		
		//validation for experience
		
		if(expLength==0) {
			System.out.println("Alphaets or special characters are not allowed for experience.");
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(6) > p")).getText();
			System.out.println("Expected error for experience field: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);

		} else if (experience.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(6) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		experienceValue.clear();
		
		//validation for resume
		
//		if(!resume.matches(".doc") || !resume.matches(".pdf")  || !resume.matches(".docx")) {
//			String expected="File extension 'png' is not allowed. Allowed extensions are: 'pdf, doc, docx'.";
//			Thread.sleep(1000);
//			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(7) > p")).getText();
//			System.out.println("Expected error for name field: " + expected);
//			System.out.println("Actual error: " + actual);
//			Assert.assertEquals(actual, expected);
//		}else if (resume.matches("")) {
//			String expected="This field may not be blank.";
//			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(7) > p")).getText();
//			System.out.println("Expected error: " + expected);
//			System.out.println("Actual error: " + actual);
//			Assert.assertEquals(actual, expected);
//		}
		resumeValue.clear();
		
		//validation for description
		
		 if (description.matches("")) {
			String expected="This field may not be blank.";
			String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > p")).getText();
			System.out.println("Expected error: " + expected);
			System.out.println("Actual error: " + actual);
			Assert.assertEquals(actual, expected);
		}
		 descriptionValue.clear();
		
	}
	
	@AfterClass
	public void after() {
	//	driver.quit();
	}

}
