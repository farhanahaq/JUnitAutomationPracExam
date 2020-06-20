package techfios.com.JuniTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListItems {

	WebDriver driver;

	@Before
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\14693\\eclipse-workspace-AutomationPracticalExam\\JuniTest\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://techfios.com/test/101/");
		
	}

	@Test
	public void pToggleAllChk() throws InterruptedException {

		// Test 1: Validate when the toggle all check box is CHECKED,
		// all check boxes for list items are also CHECKED ON.

		//Declaring List element to access all list items
		List<WebElement> items = driver.findElements(By.xpath("//div[@id='todos-content']/form/ul/li/input"));

		//checked toggle all checkbox
		WebElement toggleAllChkBox = driver.findElement(By.name("allbox"));
		toggleAllChkBox.click();

		Thread.sleep(20);

		//checking if any checkbox is unselected 
		for (WebElement li : items)
			if (!li.isSelected()) {
				System.out.println("Toggle All CheckBox is Not working");
				break;
			} else {
				System.out.println("CheckBox is checked");

			}

	}// end of Test

	@Test
	public void removeSingleListItem() throws InterruptedException {
		// Test 2: Validate that a single list item could be removed
		// using the remove button when a single checkbox is selected.

		WebElement singleListItem = driver.findElement(By.name("todo[3]"));
		singleListItem.click();

		WebElement removeButton = driver.findElement(By.xpath("//input[@value = 'Remove']")); // Remove
		removeButton.click();

		try {
			waitForElement(driver, 2, singleListItem);
			Thread.sleep(2000);
			System.out.println("Single Item Removed");

		} catch (Exception e) {
			System.out.println("Item Still Present");
			e.printStackTrace();
		}

	}

	@Test
	public void removeAllToggleAllChk() throws InterruptedException {
		// Test 3: Validate that all list item could be removed using the remove button
		// and
		// when "Toggle All" functionality is on.

		// Selecting CheckBox
		WebElement toggleAllChkBox = driver.findElement(By.name("allbox"));
		WebElement todoChkBox1 = driver.findElement(By.name("todo[1]"));
		WebElement todoChkBox2 = driver.findElement(By.name("todo[2]"));

		// This will Toggle the Check box
		toggleAllChkBox.click();
		Thread.sleep(10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement removeButton = driver.findElement(By.xpath("//input[@value = 'Remove']")); // Remove
		removeButton.click();

		// when Toggle All chk box is selected, then all list items should be selected

		try {
			waitForElement(driver, 2, todoChkBox1);
			Thread.sleep(2000);
			waitForElement(driver, 2, todoChkBox2);
			System.out.println("ALL Items Removed");

		} catch (Exception e) {
			System.out.println("Item(s) Still Present");
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();

	}

	public void waitForElement(WebDriver driver, int timeInSeconds, WebElement singleListItem) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(singleListItem));
	}

}// end of class
