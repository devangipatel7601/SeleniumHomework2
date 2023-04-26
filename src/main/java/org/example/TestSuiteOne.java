package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuiteOne
{
    static String expectedRegistrationCompleteMsg = "Registration not working";
    static String expectedReferAFriendMessage = "Your message has not been sent";
    static  String expectedVotingMessage = "User cannot vote";
    static String expectedNonRegReferAFriendMsg = "Non registered customer can use email a friend feature";
    static String expectedNonRegisteredUserVoteMsg = "Non registered customer can vote";
    static String expectedCompare2ProductsMessage = "There are 2 products still after clearing List";
    static String expectedProductInShoppingCartMessage = "Wrong product in Shopping Cart";
    protected static WebDriver driver;

    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    public static void typeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    public static long timeStamp()
    {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        return timestamp1.getTime();
    }

    public static String getTextFromElement(By by)
    {
        return driver.findElement(by).getText();

    }

    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();

        //open demonopcommerce url
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @AfterMethod
   public static void closeBrowser()
    {

        driver.close();
    }

    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully() {
//click on register button
        clickOnElement(By.className("ico-register"));

        //enter first name
        typeText(By.id("FirstName"), "testFirstName");

        //enter last name
        typeText(By.id("LastName"),"testLatName");

        //enter email id
        typeText(By.name("Email"),"Testpatel"+timeStamp()+"@gmail.com");

        //enter password
        //driver.findElement(By.id("Password")).sendKeys("Test123");
        typeText(By.id("Password"),"Test123");

        //enter and confirm password
        // driver.findElement(By.id("ConfirmPassword")).sendKeys("Test123");
        typeText(By.id("ConfirmPassword"),"Test123");

        //click on register button
        // driver.findElement(By.id("register-button")).click();
        clickOnElement(By.id("register-button"));

        //verify message
        String actualmessage = getTextFromElement(By.xpath("//div[@class='result']"));
        getTextFromElement((By.xpath("//div[@class='result']")));

        //to print message
        System.out.println("message: " +actualmessage);
        Assert.assertEquals(actualmessage,expectedRegistrationCompleteMsg,"Registration not working");



    }
//  verifyUserShouldBeAbleToReferAProductToFriendSuccessfully  //*****
    @Test
    public static void verifyUserShouldBeAbleToReferAProductToFriendSuccessfully() {
        //click on Register
        clickOnElement(By.className("ico-register"));

        //Enter firstname
        typeText(By.id("FirstName"), "testFirstName");
        //enter lastname
        typeText(By.id("LastName"), "testLastName");
        //enter email address
        typeText(By.name("Email"), "Ncb56@gmail.com");
        //enter password
        typeText(By.id("Password"), "Test123");

        //confirm password
        typeText(By.id("ConfirmPassword"), "Test123");

        //click on register button
        clickOnElement(By.id("register-button"));
        //click on login button
        clickOnElement(By.className("ico-login"));
        //enter email
        typeText(By.id("Email"), "Ncb56@gmail.com");
        //enter password
        typeText(By.className("password"), "Test123");
        //click on login button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
        //click on product build your own computer
        clickOnElement(By.xpath("//a[@href=\"/build-your-own-computer\"][text()='Build your own computer']"));
        //click on email friend
        clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"][text()=\"Email a friend\"]"));
        //type friends email
        typeText(By.id("FriendEmail"), "hellofriend" + timeStamp() + "@gmail.com");
        //type your email
        // clickOnElement(By.id("YourEmailAddress"));
        //type message
        //typeText(By.id("Personal message"),"Look its good one");
        //click on send email
        clickOnElement(By.xpath("//button[@class='button-1 send-email-a-friend-button']"));
        // sout product name
        getTextFromElement(By.xpath("//a[@class='product']"));
        //sout msg has been sent
       String actualmessage = getTextFromElement(By.xpath("//div[@class='result']"));
        getTextFromElement((By.xpath("//div[@class='result']")));
        System.out.println("message: " + actualmessage);
        Assert.assertEquals(actualmessage, expectedReferAFriendMessage, "Your message has not been sent");


    }
//  VerifyRegisteredUserShouldBeAbleToVoteSuccessfully  //*****
    @Test
    public static void VerifyRegisteredUserShouldBeAbleToVoteSuccessfully(){


       //click on Register
        clickOnElement(By.className("ico-register"));

        //Enter firstname
        typeText(By.id("FirstName"), "testFirstName");
        //enter lastname
        typeText(By.id("LastName"), "testLatName");
        //enter email address
        typeText(By.name("Email"), "smith@gmail.com");
        //enter password
        typeText(By.id("Password"), "Test123");

        //confirm password
        typeText(By.id("ConfirmPassword"), "Test123");

        //click on register button
        clickOnElement(By.id("register-button"));
        //click on login button
        clickOnElement(By.className("ico-login"));
        //enter email
        typeText(By.id("Email"), "smith@gmail.com");
        //enter password
        typeText(By.className("password"), "Test123");
        //click on login button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
        //click on good text
        clickOnElement(By.xpath("//div[@class=\"poll\"]/ul/li[2]/label"));
        //click on vote button
        clickOnElement(By.xpath("//button[@class=\"button-2 vote-poll-button\"]"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        //print message //span[@clas
        String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        Assert.assertEquals(actualMessage, expectedVotingMessage,"Can't Vote");

}
//  Verify User Should Be Able To Compare 2 Products  //*****
@Test
    public static void VerifyUserShouldBeAbleToCompare2Products(){
    //click on Register
    clickOnElement(By.className("ico-register"));

    //Enter firstname
    typeText(By.id("FirstName"), "testFirstName");
    //enter lastname
    typeText(By.id("LastName"), "testLatName");
    //enter email address
    typeText(By.name("Email"), "abc123@gmail.com");
    //enter password
    typeText(By.id("Password"), "Test123");

    //confirm password
    typeText(By.id("ConfirmPassword"), "Test123");

    //click on register button
    clickOnElement(By.id("register-button"));
    //click on login button
    clickOnElement(By.className("ico-login"));
    //enter email
    typeText(By.id("Email"), "abc123@gmail.com");
    //enter password
    typeText(By.className("password"), "Test123");
    //click on login button
    clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

    //click on HTC One M8 Android L 5.0 phone compare button
    clickOnElement(By.xpath("//div[@class='item-grid']/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));

    clickOnElement(By.xpath("//span[@class= \"close\"]"));
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //click on $25 Virtual Gift card compare button

    clickOnElement(By.xpath("//div[@class='item-grid']/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     //click cross on green comparision product bar
    clickOnElement(By.xpath("//span[@class= \"close\"]"));
    //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]")));
    //click on green line
    clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]"));
    //printout mobile name
    String actualmessage = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[2]/a"));
    System.out.println("message: " + actualmessage);
    //printout card name
    String actualmessage1 = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[3]/a"));
    System.out.println("message: " + actualmessage1);
    //click on clear list button
    clickOnElement(By.className("clear-list"));
    //printout msg after clicking on clear list
    String actualmessage2 = getTextFromElement(By.className("no-data"));
    System.out.println("message :" + actualmessage2);
    Assert.assertEquals(actualmessage2,expectedCompare2ProductsMessage,"There are 2 products still after clearing List");



}
  //  Non Registered User Should Not Be Able To Email Friend  //*****
@Test
    public static void verifyNonRegisteredUserShouldNotBeAbleToEmailFriend(){
    //click on add to cart button
    clickOnElement(By.xpath("//div[@class = \"item-grid\" ]/div[2]/div/div[2]/div[3]/div[2]/button[1]"));

    //click on email a friend
    clickOnElement(By.xpath("//div[@class='email-a-friend']"));
    //type friend's email
    typeText(By.id("FriendEmail"),"tpatel@gmail.com");
    //type your email id
    typeText(By.id("YourEmailAddress"),"Testpatel@gmail.com");
    //click on send email
    clickOnElement(By.name("send-email"));
    String actualmessage = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li")).getText();

    System.out.println("Error message: " +actualmessage);
    Assert.assertEquals(actualmessage,expectedNonRegReferAFriendMsg,"\"Non registered customer can use email a friend feature\"");

}
@Test
    public static void verifyNonRegisteredUserShouldNotBeAbleToVote(){
    //click on good text option
    clickOnElement(By.id("pollanswers-2"));
    //click on VOTE button
    clickOnElement(By.id("vote-poll-1"));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("poll-vote-error")));

    //to verify message pop uo when u click on vote button
    String actualmessage = getTextFromElement(By.className("poll-vote-error"));
    //to print error messase
    System.out.println("my message: " +actualmessage);
   Assert.assertEquals(actualmessage,expectedNonRegisteredUserVoteMsg,"Non registered customer can vote");
}
@Test
public static void verifyUserShouldBeAbleToSeeProductsInShoppingCartSuccessfully(){
    //click on electronics
    clickOnElement(By.xpath("//div[@class='master-wrapper-page']/div[2]/ul/li[2]/a[@href='/electronics']"));
    clickOnElement(By.xpath("//img[@alt='Picture for category Camera & photo']"));

    //click on Leica T Mirrorless Digital Camera
    clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));

    //get product name//div[@class="product-name"]/h1
    String productname = getTextFromElement(By.xpath("//div[@class=\"product-name\"]/h1"));
    System.out.println("Print Product Name: " +productname);

    // click on ADD TO CART button
    clickOnElement(By.xpath("//div[@class=\"add-to-cart\"]/div/button"));


    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));

    //click on shopping cart on right top corner of page
    clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

    //verify & print out leica camera name text
    String productname1 = getTextFromElement(By.className("product-name"));

    System.out.println("Product in cart: "+ productname1);
    //confirm product name is same in shopping cart as what we add
    Assert.assertEquals(productname1,expectedProductInShoppingCartMessage,"Wrong product in Shopping Cart");





}

}







