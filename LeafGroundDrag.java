package week4.day2.assignments;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundDrag {

	public static ChromeDriver driver;
	public static Actions builder;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		builder = new Actions(driver);

		// Draggable
		WebElement dragEle = driver.findElement(By.xpath("//span[text()='Drag and Drop']"));
		builder.dragAndDropBy(dragEle, 200, 400).perform();

		// Droppable
		WebElement sourceEle = driver.findElement(By.xpath("//div[@id=\"form:drag_content\"]"));
		WebElement destinationEle = driver.findElement(By.id("form:drop_content"));
		builder.dragAndDrop(sourceEle, destinationEle).perform();

		// Draggable Columns
		WebElement column1 = driver.findElement(By.xpath("//th[@id='form:j_idt94:j_idt99']/span"));
		WebElement column2 = driver.findElement(By.xpath("//th[@id='form:j_idt94:j_idt95']/span"));
		builder.dragAndDrop(column1, column2).perform();

		// Draggable Rows
		Thread.sleep(3000);
		WebElement row1 = driver.findElement(By.xpath("(//td[text()='Bamboo Watch'])[2]"));
		WebElement row2 = driver.findElement(By.xpath("(//td[text()='Bracelet'])[2]"));
		builder.dragAndDrop(row2, row1).perform();

	}

}
