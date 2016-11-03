package translate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class translateFunc {

	static WebDriver driver;
	static String baseURL;
	static WebDriverWait wait; // 1 minute 
	
	public static void clearBrowser()
	{
		driver.close();
	}

	public static void init()
	{
		System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseURL = "https://translate.google.co.kr/#auto";
		wait =  new WebDriverWait(driver, 60);
	}
	
	public static String cn_To_kr(String str)
	{
		String result;
		
		result = To_en(str);
		result = To_jp(result);
		result = To_kr(result);
		
		return result;
	}
	
	
	
	public static String To_kr(String str) {
		return translate("/ko", str);
	}
	

	public static String To_jp(String str) {
		return translate("/ja", str);
	}
	
	public static String To_en(String str) {
		return translate("/en", str);
	}
	
	public static String translate(String lang1, String str)
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseURL + lang1);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@name=\"text\"]")).sendKeys(str);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id=\"result_box\"]/span")));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		List<WebElement> list = driver.findElements(By.xpath("//span[@id=\"result_box\"]/span"));
		String result="";
		for(int i=0 ; i<list.size() ; i++)
			result += (" "+list.get(i).getText());
		
		System.out.println(result);
		System.out.println("");

		return result;
	}
}
