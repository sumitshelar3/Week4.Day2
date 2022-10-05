package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh_WebTable {

	public static ChromeDriver driver;
	public static Actions builder;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		builder = new Actions(driver);

		// Click on stock market-->NSE bulk Deals
		WebElement stockMEle = driver.findElement(By.xpath("//a[@id='navbtn_stockmarket']"));
		WebElement nseEle = driver.findElement(By.xpath("//a[text()='NSE Bulk Deals']"));
		builder.click(stockMEle).click(nseEle).perform();

		// Get all the Security names
		List<WebElement> row = driver.findElements(
				By.xpath("//table[@class='table table-bordered table-condensed table-striped']/tbody//td[3]"));
		int size = row.size();
		List<String> values = new ArrayList<String>();
		System.out.println("Security names are: ");
		for (WebElement eachWebElement : row) {
			String text = eachWebElement.getText();
			values.add(text);
			System.out.println("   " + text);
		}

		// Ensure whether there are duplicate Security names
		Set<String> result = new HashSet<String>(values);
		int finalSize = result.size(); // Without duplicate size
		if (finalSize == size) {
			System.out.println("There are no duplicates found");
		} else {
			System.out.println("Duplicates are found");
		}

		// Printing the names for duplicates
		System.out.println();
		System.out.println("Duplicate values are:  ");
		for (String duplicate : result) {
			if (result.add(duplicate) == false) {
				System.out.println("   "+duplicate);
			}
		}
	}
}
