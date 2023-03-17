package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb; // Initialize the class to access the properties file for variable declaration
	@BeforeClass
	@Parameters("browser")// getting browser parameter from testng.xml
	public void setup(String br)
	{
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		options.addArguments("--remote-allow-origins=*");
		
		rb=ResourceBundle.getBundle("config");//hold the config file 
		logger=LogManager.getLogger(this.logger); //logging  for Logger
		//WebDriverManager.chromedriver().setup();
		if(br.equals("chrome"))
		{
		driver= new ChromeDriver(options);
		}
		else
		{
			driver= new EdgeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://demo.opencart.com/");
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString() {
		//RandomStringUtils is part of common lang3 dependency
		String generatedString=RandomStringUtils.randomAlphabetic(5);
	    return (generatedString);
	}
	public String StringrandomNumber()
	{
		String generatedString2= RandomStringUtils.randomNumeric(10);
	    return (generatedString2);
	}
	public String randomAlphNumeric()
	{
		String st= RandomStringUtils.randomAlphabetic(4);
		String num= RandomStringUtils.randomNumeric(3);
		return (st+"@"+num);
	}
	//capture the screen common file for execution of file
	public String captureScreen(String tname) throws IOException {
        //java.util.text
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    //java selenium package
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		//io file
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}

}
