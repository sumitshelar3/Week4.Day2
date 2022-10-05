package week4.day2.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HtmlTable {

	public static ChromeDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		List<WebElement> rowList = driver.findElements(By.xpath("//div[@class='render']//tr"));
		List<WebElement> columnList = driver.findElements(By.xpath("//div[@class='render']//th"));
		int rowSize = rowList.size();
		int columnSize = columnList.size();

		System.out.println("Number of rows:  " + rowSize);
		System.out.println("Number of Columns:  " + columnSize);
	}

}
