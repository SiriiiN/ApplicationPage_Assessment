package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersPage {
	
	//using Page Object Model 
	
    WebDriver driver;
	
	//Constructor that will be automatically called as soon as the object of the class is created
	public CareersPage(WebDriver driver) {
		super();
		this.driver=driver;
	}
	
	@FindBy(name="name")
	WebElement Name;
	
	@FindBy(name="email")
	WebElement Email;
	
	@FindBy(name="phone")
	WebElement Phone;
	
	@FindBy(name="notice_time")
	WebElement Notice_period ;
	
	@FindBy(name="experience")
	WebElement Experience;
	
	@FindBy(name="resume")
	WebElement Resume;
	
	@FindBy(xpath="//*[@id=\"contact-form\"]/div/div[6]/textarea")
	WebElement Description;
	
	@FindBy(xpath="//*[@id=\"contact-form\"]/div/div[7]/div/button[1]")
	WebElement Apply;
	
	
	public void enterName(String name) {
		Name.sendKeys(name);
	}
	
	public void enterEmail(String email) {
		Email.sendKeys(email);
	}
	
	public void enterPhone(String phone) {
		Phone.sendKeys(phone);
	}
	
	public void enterNoticePeriod(String noticePeriod) {
		Notice_period.sendKeys(noticePeriod);
	}
	
	public void enterExperience(String experience) {
		Experience.sendKeys(experience);
	}
	
	public void uploadResume(String resume) {
		Resume.sendKeys(resume);
	}
	
	public void enterDesciption(String description) {
		Description.sendKeys(description);
	}
	
	public void apply() {

		Apply.sendKeys(Keys.ENTER);
	}

}
