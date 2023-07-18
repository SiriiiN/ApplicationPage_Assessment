package com.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.data.provider.DataProviderClass;
import com.pages.CareersPage;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class Application {

	WebDriver driver;

	@BeforeTest
	public void before() throws InterruptedException {

		// creating chrome driver instance
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();

		//launching web page
		driver.get("https://www.hashtag-ca.com/careers/apply?jobCode=QAE001");
		driver.manage().window().maximize();


		WebElement element = driver.findElement(By.xpath("//*[@id=\"CcstyleSpaceIs\"]/div/div[1]/p/span"));
		//scrolling down to enter input values 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 

	}
	@Test(dataProvider="Input Data", dataProviderClass=DataProviderClass.class)
	public void applyWithValidInputs(String scenario, String name, String email, String phone, 
			String resume, String description) throws InterruptedException {

		CareersPage page= PageFactory.initElements(driver, CareersPage.class);

		//reading inputs from data provider class

		page.enterName(name);
		page.enterEmail(email);
		page.enterPhone(phone);
		page.uploadResume(resume);
		page.enterDesciption(description);
		Thread.sleep(1000);
		page.apply();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		//validating with correct inputs

		if (scenario.equals("valid input")) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#contact-form > div > div:nth-child(8) > h4")));
			Assert.assertEquals("Application Submitted Successfully.",
					driver.findElement(By.xpath("#contact-form > div > div:nth-child(8) > h4")).getText());
		}  


	}




	@Test(dataProvider = "Input Data1")
	public void applyWithDataProviderwithoutNPandExp(String scenario, String name, String email, String phone, String resume, String description) throws InterruptedException {

		//reading input values from dataProvider class
		WebElement nameValue = driver.findElement(By.name("name"));
		nameValue.sendKeys(name);


		WebElement emailValue = driver.findElement(By.name("email"));
		emailValue.sendKeys(email);


		WebElement phoneValue = driver.findElement(By.name("phone"));
		phoneValue.sendKeys(phone);
		String inputphn = driver.findElement(By.name("phone")).getAttribute("value");
		int phnLength = inputphn.length();



		WebElement resumeValue = driver.findElement(By.name("resume"));
		resumeValue.sendKeys(resume);


		WebElement descriptionValue = driver
				.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/textarea"));
		descriptionValue.sendKeys(description);


		WebElement applyValue = driver.findElement(By.xpath(
				"//*[@id=\"contact-form\"]/div/div[7]/div/button[1]"));
		applyValue.sendKeys(Keys.ENTER);


		Thread.sleep(1000);

		//clearing data before entering new list of data
		nameValue.clear();
		emailValue.clear();
		phoneValue.clear();
		resumeValue.clear();
		descriptionValue.clear();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//validating with incorrect values

		//Name validation

		if (scenario.equals("empty name")) {
			if (name.matches("")) {
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(2) > p"))
						.getText();
				System.out.println("Expected error: " + expected);
				System.out.println("Actual error: " + actual);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("#contact-form > div > div:nth-child(2) > p")));

				Assert.assertEquals(actual, expected);
			}

		} else if (scenario.equals("invalid name")) {
			if (!name.matches("[a-zA-Z]+")) {
				Assert.assertEquals("something went wrong! please try again later",
						driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText());
			}

		} 

		//email validation

		else if (scenario.equals("invalid email")) {

			if (!email.matches("[a-zA-Z+_.-]@[a-zA-Z][.com]")) {
				Assert.assertEquals("something went wrong! please try again later",
						driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText());
			}
		} else if (scenario.equals("empty email")) {
			if (email.matches("")) {
				Assert.assertEquals("something went wrong! please try again later",
						driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText());
			}
		}

		//phone number validation
		else if (scenario.equals("invalid phone number")) {
			if (phnLength == 0) {
				System.out.println("Alphaets or special characters are not allowed for phone number.");
				Assert.assertEquals("something went wrong! please try again later",
						driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText());

			} else if (inputphn.matches(".+eE")) {
				// LengthCheck =true;
				System.out.println("Only numbes are allowed for phone number.");
				String expected = "something went wrong! please try again later";
				String actual = driver
						.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText();
				System.out.println("Expected error for phone field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(expected, actual);

			} else if (phnLength < 0) {
				System.out.println("Invalid Phone number. Phone number must have 10 digits.");
			} else if (phone.matches("")) {
				Assert.assertEquals("something went wrong! please try again later",
						driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
						.getText());
			}

		} 
		//Resume validation
		else if (scenario.equals("invalid resume")) {
			if(!resume.matches(".doc") || !resume.matches(".pdf")  || !resume.matches(".docx")) {
				String expected="File extension 'png' is not allowed. Allowed extensions are: 'pdf, doc, docx'.";
				Thread.sleep(1000);
				String actual=driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(7) > p")).getText();
				System.out.println("Expected error for name field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);
			}

		}
		//Description validation
		else if (scenario.equals("empty description")) {
			Assert.assertEquals("something went wrong! please try again later",
					driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
					.getText());
			
		}




		//validation with not passing any data
		else if (scenario.equals("empty fields")) {
			Assert.assertEquals("something went wrong! please try again later",
					driver.findElement(By.xpath("//*[@id=\"contact-form\"]/div/div[6]/p[2]"))
					.getText());


		}
	}


	//@Test(dataProvider = "Input Data2")
	public void applyWithDataProviderwithNPandExp(String scenario, String name, String email, String phone, String noticePeriod,
			String experience, String resume, String description) throws InterruptedException {
		CareersPage page = PageFactory.initElements(driver, CareersPage.class);


		WebElement nameValue = driver.findElement(By.name("name"));
		nameValue.sendKeys(name);


		WebElement emailValue = driver.findElement(By.name("email"));
		emailValue.sendKeys(email);


		WebElement phoneValue = driver.findElement(By.name("phone"));
		phoneValue.sendKeys(phone);
		String inputphn = driver.findElement(By.name("phone")).getAttribute("value");
		int phnLength = inputphn.length();


		WebElement npValue = driver.findElement(By.name("notice_time"));
		npValue.sendKeys(noticePeriod);
		String inputNP = driver.findElement(By.name("notice_time")).getAttribute("value");
		int npLength = inputNP.length();


		WebElement experienceValue = driver.findElement(By.name("experience"));
		experienceValue.sendKeys(experience);
		String inputExp = driver.findElement(By.name("experience")).getAttribute("value");
		int expLength = inputExp.length();

		WebElement resumeValue = driver.findElement(By.name("resume"));
		resumeValue.sendKeys(resume);


		WebElement descriptionValue = driver
				.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > textarea"));
		descriptionValue.sendKeys(description);


		WebElement applyValue = driver.findElement(By.cssSelector(
				"#contact-form > div > div.col-lg-10.col-md-12.col-sm-12.pt-5.pb-5.d-flex.justify-content-center > div > button.btn.form-button.font-12.font-bold"));
		applyValue.sendKeys(Keys.ENTER);


		Thread.sleep(1000);

		//clearing data before entering new list of data
		nameValue.clear();
		emailValue.clear();
		phoneValue.clear();
		npValue.clear();
		experienceValue.clear();
		resumeValue.clear();
		descriptionValue.clear();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//validating with incorrect values

		//Name validation

		if (scenario.equals("empty name")) {
			if (name.matches("")) {
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(2) > p"))
						.getText();
				System.out.println("Expected error: " + expected);
				System.out.println("Actual error: " + actual);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("#contact-form > div > div:nth-child(2) > p")));

				Assert.assertEquals(actual, expected);
			}

		} else if (scenario.equals("invalid name")) {
			if (!name.matches("[a-zA-Z]+")) {
				String expected = "Only alphabets, space, quote(') and dot(.) is supported.";
				Thread.sleep(1000);
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(2) > p"))
						.getText();
				System.out.println("Expected error for name field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);
			}

		} 

		//email validation

		else if (scenario.equals("invalid email")) {

			if (!email.matches("[a-zA-Z+_.-]@[a-zA-Z][.com]")) {
				String expected = "Enter a valid email address.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(3) > p"))
						.getText();
				System.out.println("Expected error for email field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);
			}
		} else if (scenario.equals("empty email")) {
			if (email.matches("")) {
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(3) > p"))
						.getText();
				System.out.println("Expected error: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);
			}
		}

		//phone number validation
		else if (scenario.equals("invalid phone number")) {
			if (phnLength == 0) {
				System.out.println("Alphaets or special characters are not allowed for phone number.");
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(4) > p"))
						.getText();
				System.out.println("Expected error for phone field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);

			} else if (inputphn.matches(".+eE")) {
				// LengthCheck =true;
				System.out.println("Only numbes are allowed for phone number.");
				String expected = "something went wrong! please try again later";
				String actual = driver
						.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > p:nth-child(5)"))
						.getText();
				System.out.println("Expected error for phone field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(expected, actual);

			} else if (phnLength < 0) {
				System.out.println("Invalid Phone number. Phone number must have 10 digits.");
			} else if (phone.matches("")) {
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(4) > p"))
						.getText();
				System.out.println("Expected error: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);
			}

		} 

		//notice period validation
		else if (scenario.equals("invalid notice period")) {
			if (npLength == 0) {
				System.out.println("Alphaets or special characters are not allowed for nptice period.");
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(5) > p"))
						.getText();
				System.out.println("Expected error for notice period field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);

			}
		} 

		//experience validation
		else if (scenario.equals("invalid experience")) {
			if (expLength == 0) {
				System.out.println("Alphaets or special characters are not allowed for experience.");
				String expected = "This field may not be blank.";
				String actual = driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(6) > p"))
						.getText();
				System.out.println("Expected error for experience field: " + expected);
				System.out.println("Actual error: " + actual);
				Assert.assertEquals(actual, expected);

			}
		} 
		//validation with not passing any data
		else if (scenario.equals("empty fields")) {
			Assert.assertEquals("something went wrong! please try again later",
					driver.findElement(By.cssSelector("#contact-form > div > div:nth-child(8) > p:nth-child(5)"))
					.getText());


		}
	}




}



