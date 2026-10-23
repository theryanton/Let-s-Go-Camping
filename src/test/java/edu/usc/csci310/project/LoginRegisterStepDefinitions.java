package edu.usc.csci310.project;

import edu.usc.csci310.project.repositories.UserRepository;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginRegisterStepDefinitions {
    //so that port can be modified if needed
    @LocalServerPort
    private int port = 8080;
    String ROOT_URL = "http://localhost:" + port + "/";

    @Autowired
    private UserRepository userRepository;

    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(10);

    private static final WebDriver driver = new ChromeDriver();

    private static final WebDriverWait wbw = new WebDriverWait(driver, TIMEOUT_SECONDS);

    @Given("a user is on the registration page")
    public void aUserIsOnTheRegistrationPage() {
        driver.get(ROOT_URL + "UserRegistration");
    }

    @Given("a user is on the login page")
    public void aUserIsOnTheLoginPage() {
        driver.get(ROOT_URL + "Login");
    }

    @And("the user enters the valid username {string}")
    public void theUserEntersTheValidUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @And("the user enters the username {string}")
    public void theUserEntersTheUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @And("the user enters the password {string}")
    public void theUserEntersThePassword(String password) {
        //do nothing for now
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("the user enters an invalid username")
    public void theUserEntersAnInvalidUsername() {
        driver.findElement(By.id("username")).sendKeys(" ");
    }

    @And("the user enters an incorrect password")
    public void theUserEntersAnIncorrectPassword() {
        driver.findElement(By.id("password")).sendKeys(" ");
    }

    @And("the user clicks the cancel button")
    public void theUserClicksTheCancelButton() {
        driver.findElement(By.className("cancel-btn")).click();
    }

    @And("the user confirms the password {string}")
    public void theUserConfirmsThePassword(String confirm) {
        driver.findElement(By.id("confirm_password")).sendKeys(confirm);
    }

    @And("the user clicks the exit button")
    public void theUserClicksTheExitButton() {
        driver.findElement(By.className("confirm-cancel-btn")).click();
    }

    @And("the form keeps username {string}, password {string}, and confirm {string}")
    public void theFormShouldPreserveUsernamePasswordAndConfirm(String username, String password, String confirm) {
        String foundUsername = driver.findElement(By.id("username")).getAttribute("value");
        String foundPassword = driver.findElement(By.id("password")).getAttribute("value");
        String foundConfirm = driver.findElement(By.id("confirm_password")).getAttribute("value");

        assertEquals(username, foundUsername);
        assertEquals(password, foundPassword);
        assertEquals(confirm, foundConfirm);
    }

    @When("the user clicks Never Mind!")
    public void theUserClicksNeverMind() {
        driver.findElement(By.className("continue-btn")).click();
    }

    @When("the user clicks the register button")
    public void theUserClicksTheRegisterButton() {
        driver.findElement(By.className("register-btn")).click();
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        //SUBMIT RESULTS
        driver.findElement(By.className("login-btn")).click();

    }

    @Then("the confirm cancellation query should be displayed")
    public void theConfirmCancellationQueryShouldBeDisplayed() {
        wbw.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        WebDriverWait wbw = new WebDriverWait(driver, TIMEOUT_SECONDS);

        wbw.until(ExpectedConditions.urlMatches("http://localhost:8080/Login"));

        String get_url = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/Login",get_url);
    }

    @Then("error {string} is returned")
    public void theErrorMessageShouldBeReturned(String error) {
        WebElement message = wbw.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        assertEquals(error, message.getText());
    }

    @Then("the confirm cancellation query should be hidden")
    public void theConfirmCancellationQueryShouldBeHidden() {
        List<WebElement> list = driver.findElements(By.className("modal"));
        assertEquals(0, list.size());
    }

    @When("I click on the link to the registration page in the navbar LoginReg")
    public void iClickOnRegistration_LoginReg() {
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[1]/nav/ul/li[3]/a"));
        button.click();
    }

    @Then("I should be redirected to the registration page LoginReg")
    public void iRedirectedToRegistration_LoginReg() {
        assertEquals(driver.getCurrentUrl(), "http://localhost:8080/UserRegistration");
    }

    @And("I am logged in LoginReg")
    public void iAmLoggedIn_LoginReg() {
        // Get all the cookies
        Set<Cookie> cookies = driver.manage().getCookies();

        // Check if the desired cookie exists
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_id")) {
                // If the cookie is found, assert and return
                assertEquals("user_id", cookie.getName());
                return;
            }
        }

        // If the cookie is not found, fail the test
        throw new AssertionError("Expected cookie 'user_id' is not loaded onto the page.");
    }

    @When("I click on the link to the favorites page in the navbar LoginReg")
    public void iClickOnFavorites_LoginReg() {

    }

    @Then("I should be redirected to the favorites page LoginReg")
    public void iRedirectedToFavorites_LoginReg() {

    }

    @When("I click on the link to the login page in the navbar LoginReg")
    public void iClickToLogin_LoginReg() {
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[1]/nav/ul/li[1]/a"));
        button.click();
    }

    @Then("I should be redirected to the login page LoginReg")
    public void iRedirectedToLogin_LoginReg() {
        assertEquals("http://localhost:8080/Login", driver.getCurrentUrl());
    }

    @When("I click on the link to the search page in the navbar LoginReg")
    public void iClickOnSearch_LoginReg() {
        // Assuming 'searchLink' is the ID of the link to the search page
        WebElement searchLink = driver.findElement(By.id("searchLink"));
        searchLink.click();

    }
    @Then("I should be redirected to the search page LoginReg")
    public void iRedirectedToSearch_LoginReg() {
        // This waits up to 10 seconds until the URL is the expected one
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.urlToBe(ROOT_URL + "SearchPage")); // Make sure this matches the actual URL
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(ROOT_URL + "SearchPage", currentUrl);
    }

    @After
    public void resetRegister() {
        userRepository.findByUsername("ABC").ifPresent(user -> userRepository.delete(user));
    }
}
