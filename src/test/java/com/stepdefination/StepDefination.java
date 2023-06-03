package com.stepdefination;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.orangehrm.entity.Employee;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.OrangehrmEmployeePage;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefination {

	WebDriver driver = null;
	LoginPage loginPage = null;
	OrangehrmEmployeePage empPage = null;
	Employee emp = null;

	@Given("user launches the browser")
	public void user_launches_the_browser() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}

	@Given("opens the url {string}")
	public void opens_the_url(String url) throws InterruptedException {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		Thread.sleep(3000);

		loginPage = new LoginPage(driver);
	}

	@When("user enters the username as {string} and password as {string}")
	public void user_enters_the_username_as_and_password_as(String username, String password) {

		loginPage.setUserName(username);
		loginPage.setPassword(password);
	}

	@When("clicks on the login button")
	public void clicks_on_the_login_button() {

		loginPage.clickLogin();

	}

	@Then("the user should see the homepage of OrangeHRM")
	public void the_user_should_see_the_homepage_of_orange_hrm() {

		if (loginPage.isSuccesfulLogin()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Given("user launches the browser for orangeHRM")
	public void user_launches_the_browser_for_orange_hrm() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

	}

	@Given("opens the orange HRM url {string}")
	public void opens_the_orange_hrm_url(String url) throws InterruptedException {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		Thread.sleep(3000);

		loginPage = new LoginPage(driver);
		empPage = new OrangehrmEmployeePage(driver);

	}

	@When("user login with the username as {string} and password as {string}")
	public void user_login_with_the_username_as_and_password_as(String username, String password) {

		loginPage.setUserName(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();

		if (loginPage.isSuccesfulLogin()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@When("clicks on the add employee button")
	public void clicks_on_the_add_employee_button() {

		empPage.clickOnAddEmployeeButton();

	}

	@When("user enters firstname as {string} , lastname as {string} and gender as {string}")
	public void user_enters_firstname_as_lastname_as_and_gender_as(String firstName, String lastName, String gender)
			throws InterruptedException {

		emp = new Employee(firstName, lastName, gender);
		empPage.addEmployee(emp);

	}

	@Then("the user should see the added employee of OrangeHRM")
	public void the_user_should_see_the_added_employee_of_orange_hrm() {

		Assert.assertTrue(empPage.isEmployeeAdded(emp.getFirstName(), emp.getLastName()));

	}

	@After
	public void closeBrowser(Scenario s) {

		if (s.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] data = ts.getScreenshotAs(OutputType.BYTES);
			s.attach(data, "image/png", s.getName());
		}

		driver.quit();

	}

}
