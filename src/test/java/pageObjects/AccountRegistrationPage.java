package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// Elements
		@FindBy(name = "firstname")
		WebElement txtFirstname;

		@FindBy(name = "lastname")
		WebElement txtLasttname;

		@FindBy(name = "email")
		WebElement txtEmail;

		@FindBy(name = "telephone")
		WebElement txtTelephone;

		@FindBy(name = "password")
		WebElement txtPassword;

		@FindBy(name = "confirm")
		WebElement txtConfirmPassword;

		@FindBy(name = "agree")
		WebElement chkdPolicy;

//		@FindBy(xpath = "//input[@value='Continue']")
//		WebElement btnContinue;
		
		@FindBy(xpath = "//button[normalize-space()='Continue']")
		WebElement btnContinue;
		//button[normalize-space()='Continue']

		@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgConfirmation;
		
		@FindBy(xpath="//h1[normalize-space()='Register Account']")
		WebElement checkRegistration;

		public void setFirstName(String fname) {
			txtFirstname.sendKeys(fname);

		}

		public void setLastName(String lname) {
			txtLasttname.sendKeys(lname);

		}

		public void setEmail(String email) {
			txtEmail.sendKeys(email);

		}

		public void setTelephone(String tel) {
			txtTelephone.sendKeys(tel);

		}

		public void setPassword(String pwd) {
			txtPassword.sendKeys(pwd);

		}

		public void setConfirmPassword(String pwd) {
			txtConfirmPassword.sendKeys(pwd);

		}

		public void setPrivacyPolicy() {
			//Using JavaScript click it worked but we bypassed the actual reason which may be a potential bug or a bad UX design
			//ElementClickInterceptedException Indicates that a click could not be properly executed because the target element was obscured in some way. This exception class extends ElementNotInteractableException class.
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", chkdPolicy);
			//chkdPolicy.click();

		}

		public void clickContinue() {
			//sol1 
			//btnContinue.click();
			
			//sol2 
			//btnContinue.submit();
			
			//sol3
			//Actions act=new Actions(driver);
			//act.moveToElement(btnContinue).click().perform();
						
			//sol4
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", btnContinue);
			
			//Sol 5
			//btnContinue.sendKeys(Keys.RETURN);
			
			//Sol6  
//			WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
			
		}
		public String getCheckRegistration()
		{
			return(checkRegistration.getText());
		}

		public String getConfirmationMsg() {
			try {
				return (msgConfirmation.getText());
			} catch (Exception e) {
				return (e.getMessage());

			}

		}
}