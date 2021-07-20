package com.qa.pages;

//import org.apache.http.auth.UsernamePasswordCredentials;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


public class login extends TestBase{
		
	//Initializing the Page Objects:
	public login(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	
	public void signin()
	{
        driver.findElement(By.xpath("//input[@id='employrcode']")).sendKeys("Hiretech12");
        driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys("kiran@sails.solutions");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Sails@13");
        driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();
	}


	public void logout()
	{
		driver.findElement(By.xpath("//li[@class='header--logout']")).click();
	}

	
}
