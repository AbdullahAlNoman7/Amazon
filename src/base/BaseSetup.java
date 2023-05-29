package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSetup {

	public static WebDriver driver;
	
	public static void driverSetup() {
		System.setProperty("Webdriver.driver.chrome", "G:\\JavaSelenium\\Amazon\\driver\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void navigateUrl(String url) {
		driver.get(url);
	}
	public static void sendKeys(By by,String keyValue ) {
		driver.findElement(by).sendKeys(keyValue);
	}
	public static void click(By by) {
		driver.findElement(by).click();
	}
	public static String getText(By by) {
		return driver.findElement(by).getText();
	}
	public static void tearDown() {
		driver.close();
	} 
}
