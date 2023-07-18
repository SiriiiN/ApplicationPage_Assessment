package com.data.provider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name="Input Data")
	public  Object[][] dataProvider(){
		return new Object[][] {

			{"valid input","abcd","abc_1@gmail.com","9678987771","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing,"
					+ "				+ \" developing, and executing automated test scripts"}

		};
	}

	@DataProvider(name="Input Data1")
	public  Object[][] dataProvider1(){
		return new Object[][] {
			{"empty fields","","","","",""},
			{"empty name","","abc_1@gmail.com","9678987771","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing,"
					+ " developing, and executing automated test scripts"},
			{"invalid name","abcd123","abc_1@gmail.com","9678987771","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid email","abcd","abc_1#gmail.com","abcd","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"empty email","abcd","","9678987771","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid phone number","abcd","abc_1@gmail.com","abcd","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"empty description experience","abcd","abc_1@gmail.com","9678987771","C://Myfiles//ABCD Resume.docx",""},
			{"invalid resume","abcd","abc_1@gmail.com","9678987771","C://Users//DELL//OneDrive//Pictures//27511score.png","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"}


		};
	}

	@DataProvider(name="Input Data2")
	public  Object[][] dataProvider2(){
		return new Object[][] {
			{"empty fields","","","","","","",""},
			{"empty name","","abc_1@gmail.com","9678987771","","","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing,"
					+ " developing, and executing automated test scripts"},
			{"invalid name","abcd123","abc_1@gmail.com","9678987771","90","3","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid email","abcd","abc_1#gmail.com","abcd","90","3","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"empty email","abcd","","9678987771","ab","3","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid phone number","abcd","abc_1@gmail.com","abcd","90","ab","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid notice period","abcd","abc_1@gmail.com","9678987771","ab","3","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid experience","abcd","abc_1@gmail.com","9678987771","90","3","C://Myfiles//ABCD Resume.docx","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"},
			{"invalid resume","abcd","abc_1@gmail.com","9678987771","90","3","C://Users//DELL//OneDrive//Pictures//27511score.png","Highly skilled automation tester with 3 years of experiencein designing, developing, and executing automated test scripts"}


		};
	}



}
