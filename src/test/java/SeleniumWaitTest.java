import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.List;

public class SeleniumWaitTest {

    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //global timeout, driver will be put to the timeout
    }

    @Test
    public void testName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"start\"]/button")).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"finish\"]/h4")));
        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"finish\"]/h4"));
        String text = element.getText();
        Assert.assertEquals(text, "Hello World");
    }

    @Test
    public void testCheck() {

        WebElement element = driver.findElement(By.xpath("//*[@id='checkbox']/input"));
        if (!element.isSelected()) {
            element.click();
        }
        driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
        Assert.assertEquals(message.getText(), "It's gone!");


        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.sendKeys("Learning Selenium");

        if (webElement.isEnabled()) {
            System.out.printf("Its enabled");
        }
    }

        @AfterSuite
        public void tearDown () throws InterruptedException {
            Thread.sleep(1000);
            driver.quit();
        }

    }

