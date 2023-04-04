package com.example.CucumberLab3;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinition {

    private static WebDriver driver;

    static void setupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    void navigate() {
        driver.get("https://svtplay.se");

        WebElement acceptCookiesButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button[3]"));

        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    @Given("SVT Play is available")
    public void svt_play_is_available() {
        setupWebDriver();
        navigate();
    }
    @When("User visits SVT Play")
    public void user_visits_svt_play() {
        driver.manage().window().maximize();
    }
    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "Titel is not correct.");
    }

    @Then("The logo should be visible")
    public void the_logo_should_be_visible() {
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a"));
        Boolean isVisible = logo.isDisplayed();
        assertTrue(isVisible, "Logo is not visible.");
    }

    @Then("The main content should be visible")
    public void the_main_content_should_be_visible() {
        WebElement main = driver.findElement(By.id("play_main-content"));
        Boolean result = main.isDisplayed();
        assertTrue(result, "Main content is not visible.");
    }

    @Then("Contact URL should be {string}")
    public void contact_url_should_be(String expectedUrl) {
        List<WebElement> contact = driver.findElements(By.className("sc-5b00349a-0"));
        String actualLinkText = contact.get(1).getAttribute("href");
        String expectedLinkText = "https://kontakt.svt.se/";
        assertEquals(expectedLinkText, actualLinkText, "Contact URL does not seem to be correct.");
    }

    @Then("The main menu link texts should be {string}, {string}, {string}")
    public void the_main_menu_link_texts_should_be(String excpectedStartText, String excpectedProgramText, String excpectedKanalerText) {
        String actualStartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[1]")).getText();
        String actualProgramText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a")).getText();
        String actualKanalerText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]/a")).getText();

        assertAll(
                () -> assertEquals(excpectedStartText, actualStartText, "The Start text is not correct."),
                () -> assertEquals(excpectedProgramText, actualProgramText, "The Program text is not correct."),
                () -> assertEquals(excpectedKanalerText, actualKanalerText, "The Kanaler text is not correct.")
        );
    }

    @When("We navigate to Popular category")
    public void we_navigate_to_popular_category() {
        driver.get("https://www.svtplay.se/populara");
    }
    @Then("Header text should be {string}")
    public void header_text_should_be(String expectedHeaderText) {
        WebElement pageHeader = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/h1"));
        String actualHeaderText = pageHeader.getText();
        assertEquals(expectedHeaderText, actualHeaderText, "Navigating to Populär does not seem to be working.");
    driver.quit();
    }

    @Given("User is on main page")
    public void user_is_on_main_page() {
        setupWebDriver();
        navigate();
    }

    @When("When we search for the show Rederiet")
    public void when_we_search_for_the_show_rederiet() {
        driver.manage().window().maximize();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.name("q"))).click();
        } catch (Exception e) {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[5]/form/input"))).sendKeys("Rederiet");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[5]/form/button")).click();
        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a/div[2]/h2/span/em")));

    }
    @Then("{string} should appear in our search result")
    public void should_appear_in_our_search_result(String expectedProgramName) {
        WebElement programName = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a/div[2]/h2/span/em"));
        String actualProgramName = programName.getText();
        assertEquals(expectedProgramName,actualProgramName, "The search box does not seem to function correctly.");

        driver.quit();
    }


    @Given("SVT Play main page is available")
    public void svt_play_main_page_is_available() {
        setupWebDriver();
        navigate();
    }
    @When("User scrolls all the way down")
    public void user_scrolls_all_the_way_down() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    @Then("The availability link should be visible")
    public void the_availability_link_should_be_visible() {
        WebElement availabilityLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));
        Boolean isVisible = availabilityLink.isDisplayed();
        assertTrue(isVisible, "Availability link seems to be not visible.");
    }

    @Then("The link text should be {string}")
    public void the_link_text_should_be(String expectedText) {
        WebElement linkText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]"));
        String actualText = linkText.getText();
        assertEquals(expectedText, actualText, "The text is not correct.");
        driver.quit();
    }

    @Given("Availability page is available")
    public void availability_page_is_available() {
        setupWebDriver();
        driver.get("https://www.svt.se/kontakt/sa-arbetar-svt-med-tillganglighet");
    }
    @When("User visits availability page")
    public void user_visits_availability_page() {
        driver.manage().window().maximize();
    }
    @Then("The main header text should be {string}")
    public void the_main_header_text_should_be(String expectedHeaderText) {
        String actualHeaderText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/h1")).getText();
        assertEquals(expectedHeaderText, actualHeaderText, "Header text is not correct.");
        driver.quit();
    }

    @When("Navigating to the Program page")
    public void navigating_to_the_program_page() {
        driver.manage().window().maximize();

        try {
            WebElement programLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a"));
            programLink.click();
        } catch (Exception e) {
            WebElement programLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a"));
            programLink.click();
        }
    }
    @Then("{int} categories should be listed")
    public void categories_should_be_listed(Integer expectedNumberOfCategories) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sc-a9073dc0-0.fVSyGp.sc-3b830fc0-4.dEXIAv")));
        List<WebElement> list = driver.findElements(By.cssSelector(".sc-a9073dc0-0.fVSyGp.sc-3b830fc0-4.dEXIAv"));
        assertEquals(expectedNumberOfCategories, list.size(), "The number of categories is not correct.");
    }
    @Then("The show all categories button should be visible")
    public void the_show_all_categories_button_should_be_visible() {
        WebElement showCategoriesButton = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/div/a"));
        assertTrue(showCategoriesButton.isDisplayed(), "The show all categories button is not visible.");
        driver.quit();
    }

}
