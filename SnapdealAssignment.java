package week4.day2.assignments;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealAssignment {

	public static ChromeDriver driver;
	public static Actions builder;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disabled-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		builder = new Actions(driver);

		// MouseHover on Men's Fashion & click on Sports Shoes
		WebElement mensFashion = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		WebElement sportsShoes = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
		builder.moveToElement(mensFashion).click(sportsShoes).perform();

		// Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		String sportShoesCount = text.replaceAll("\\D", "");
		System.out.println("Count of the sports shoes are:  " + sportShoesCount);

		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		// Sort by Low to High
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();

		Thread.sleep(5000);
		List<WebElement> sortl = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<String> shoesList = new ArrayList<String>();
		for (int i = 0; i < sortl.size(); i++) {
			String text2 = sortl.get(i).getText();
			String result = text2.replaceAll("\\D", "");
			shoesList.add(result);
			int parseInt = Integer.parseInt(result);
			System.out.println("Real output: " + parseInt);
		}

		// Select the price range (900-1500)
		Thread.sleep(2000);
		WebElement rangeStart = driver.findElement(By.name("fromVal"));
		rangeStart.clear();
		rangeStart.sendKeys("900");
		WebElement rangeEnd = driver.findElement(By.name("toVal"));
		rangeEnd.clear();
		rangeEnd.sendKeys("1500");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		// Filter with Any present color like Blue
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[@for='Color_s-Blue']")).click();
		// verify the all applied filters
		String text2 = driver.findElement(By.xpath("(//div[@class='navFiltersPill'])/a[1]")).getText();
		System.out.println(text2);// Get the Price filter text
		Thread.sleep(2000);
		String text3 = driver.findElement(By.xpath("//div[@class='navFiltersPill']/following::a")).getText();
		System.out.println(text3);// // Get the Color filter text

		if ((text2.contains("900")) && (text3.contains("Blue"))) {
			System.out.println("Validation:Filter is applied as per requirement");
		} else {
			System.out.println("Validation:Filter is not applied as per requirement");
		}

		// Mouse Hover on first resulting Training shoes & click QuickView button
		WebElement firstEle = driver.findElement(By.xpath("//picture[@class='picture-elem']/img"));
		WebElement quickView = driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div"));
		builder.moveToElement(firstEle).click(quickView).perform();
		WebElement snap = driver.findElement(By.xpath("//ul[@id='bx-slider-qv-image-panel']//img"));

		// Print the cost and the discount percentage
		String text4 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String text5 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		String price = text4.replaceAll("\\D", "");
		String discount = text5.replaceAll("\\D", "");
		System.out.println("Price for product is: " + price);
		System.out.println("Discount percentages are: " + discount);

		// Take the snapshot of the shoes
		File screenshotAs = snap.getScreenshotAs(OutputType.FILE);
		File target = new File("./SnapFolder/pic.png");
		FileUtils.copyFile(screenshotAs, target);

		// Close the current window
		// driver.close(); 

		// Close all windows
		driver.quit();

	}

}
