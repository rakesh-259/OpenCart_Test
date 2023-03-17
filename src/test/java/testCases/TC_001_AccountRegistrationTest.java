package testCases;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass 
{

	
		@Test
		public void test_account_Registration() throws InterruptedException
		{
			
			logger.info("*** stsrting TC_001_AccountRegistrationTest ***");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on my account link");
			hp.clickRegister();
			logger.info("Cliked on register link");
			AccountRegistrationPage regpage =new AccountRegistrationPage(driver);
			logger.info("Providing customer data");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			String password=randomAlphNumeric();
			regpage.setPassword(password);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			String checkReg=regpage.getCheckRegistration();
			Assert.assertEquals(checkReg, "Register Account");
			//String comfmsg=regpage.getConfirmationMsg();
			//logger.info("Validating expected message");
			//Assert.assertEquals(comfmsg, "Your Account Has Been Created!");
			logger.info("*** Finished TC_001_AccountRegistrationTest***");
			
			
		}
		


}
