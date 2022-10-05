package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaAssignment {

	public static ChromeDriver driver;
	public static Actions builder;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		builder = new Actions(driver);

		//Mouse hover on Brands and click on L'Oreal Paris
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement brandname = driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]"));
		builder.moveToElement(brand).pause(2000).click(brandname).perform();

		// Check the title contains L'Oreal Paris or not??
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title contains the name(L'Oreal Paris)");
		}else {
			System.out.println("Title does not contains the name (L'Oreal Paris)");
		}

		// Click on sort By and select customer top rated
		Thread.sleep(3000);
		WebElement sortBy = driver.findElement(By.xpath("//span[text()='Sort By : popularity']"));
		builder.click(sortBy).perform();
		WebElement cutomerTopRated = driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']//span"));
		builder.click(cutomerTopRated).perform();

		// Click Category -->click Hair -->Click haircare -->Shampoo
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='title '][text()='Category']")).click();
		driver.findElement(By.xpath("//span[@class='filter-name '][text()='Hair']")).click();
		driver.findElement(By.xpath("//span[@class='filter-name '][text()='Hair Care']")).click();
		WebElement shampooEle = driver.findElement(By.xpath("//label[@class='control control-checkbox']//span[text()='Shampoo']"));
		builder.click(shampooEle).perform();

		// Click -->Concern -->Color Protection
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='filter-open css-1kwl9pj']/span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		// Check whether the Filter is applied with Shampoo
		String filterText = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		if (filterText.equalsIgnoreCase("Shampoo")) {
			System.out.println("Validation : Filter is applied with Shampoo");
		} else {
			System.out.println("Check filter again,its not applied");
		}

		// Click on L'Oreal Paris Colour Protect Shampoo
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();

		// Switching to new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfW = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listOfW.get(1));

		// Select size as 175ml
		Thread.sleep(3000);
		WebElement sizeInMl = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select dropDownSize = new Select(sizeInMl);
		dropDownSize.selectByVisibleText("175ml");

		// Print the MRP of the product
		String text = driver.findElement(By.xpath("(//span[text()='MRP:']/following-sibling::span)[1]")).getText();
		String mrpOfProduct = text.replaceAll("[^a-zA-Z0-9]", "");
		System.out.println("MRP Prize of selected product: "+ mrpOfProduct);

		//Click on ADD to BAG
		driver.findElement(By.xpath("//div[@class='css-vp18r8']//span")).click();

		//Go to Shopping Bag 
		driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']//button")).click();

		//Switch to frame
		WebElement frame = driver.findElement(By.xpath("//iframe[@src='/mobileCartIframe?ptype=customIframeCart']"));
		driver.switchTo().frame(frame);

		//Print the Grand Total amount- Step 14
		Thread.sleep(3000);
		String text2 = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();
		String grandTotalAmt = text2.replaceAll("[^a-zA-Z0-9]", ""); // 14 Step

		// Click Proceed & move back to parentFrame
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.switchTo().parentFrame();

		// Click on Continue as Guest 
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//Check if this grand total is the same in step 14
		String lastGrandTotalAmt = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//following-sibling::span")).getText();
		
		if (grandTotalAmt.equals(lastGrandTotalAmt)) {
			System.out.println("Verified Grand Total Amount Successfully");
		} else {
			System.out.println("Grand Total Amount is not matching");
		}
		
		//Close all windows
		driver.quit();

	}

}
