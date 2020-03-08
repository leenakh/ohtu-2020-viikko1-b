package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.Random;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} and password {string} are entered")
    public void nonexistentUsernameAndPasswordAreEntered(String username, String password) {
        logInWith(username, password);
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndMatchingConfirmationAreEntered(String username, String password) {
        createNewAccount(username, password);
        confirmPassword(password);
    }
    
    @When("invalid username {string} and valid password {string} are entered")
    public void invalidUsernameAndValidPasswordAreEntered(String username, String password) {
        createNewAccount(username, password);
        confirmPassword(password);
    }
    
    @When("valid username {string} and invalid password {string} are entered")
    public void validUsernameAndInvalidPasswordAreEntered(String username, String password) {
        createNewAccount(username, password);
        confirmPassword(password);
    }
    
    @When("valid username {string} and valid password {string} and invalid password confirmation {string} are entered")
    public void passwordAndPasswordConfirmationDoNotMatch(String username, String password, String passwordConfirmation) {
        createNewAccount(username, password);
        confirmPassword(passwordConfirmation);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("user is not created and error {string} is reported")
    public void errorIsReported(String pageContent) {
        pageHasContent(pageContent);
    }

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Then("a new user is created")
    public void newUserIsCreated() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }
    
    private void createNewAccount(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
    }
    
    private void confirmPassword(String confirmation) {
        WebElement element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element.submit();
    }   
}
