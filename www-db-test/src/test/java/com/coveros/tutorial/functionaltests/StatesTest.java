package com.coveros.tutorial.functionaltests;

import io.github.bonigarcia.wdm.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Selenium test for the States page.
 */
public class StatesTest {
    /**
     * URL to start testing at.
     */
    private static final String BASE_URL = "http://192.168.33.11/";

    /**
     * Poll periodically for page load to complete.
     */
    private static final long SLEEP_MILLIS = 500L;

    /**
     * Timeout if page is not loaded in this time.
     */
    private static final long TIMEOUT_MILLIS = 60L * 1000L;

    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public static void setupClass() {
        FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().setup();
        //EdgeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new EdgeDriver();
        baseUrl = BASE_URL;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void testStates() {
        driver.get(baseUrl + "/");
        for (long second = 0; ; second += SLEEP_MILLIS) {
            if (second >= TIMEOUT_MILLIS) {
                fail("Page failed to load in " + TIMEOUT_MILLIS + "ms.");
            }
            final WebElement installed = driver.findElement(By.cssSelector("p#installed"));
            final String installedText = installed.getText();
            if (installedText.startsWith("This web server and template were installed to")) {
                break;
            }
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (final InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ie);
            }
        }

        assertEquals("State", driver.findElement(By.cssSelector("th")).getText());
        assertEquals("Ontario", driver.findElement(By.xpath("//tr[9]/td[1]")).getText());
        //assertEquals("Florida", driver.findElement(By.xpath("//tr[9]/td[1]")).getText());
        //assertEquals("Virginia", driver.findElement(By.xpath("//tr[46]/td[1]")).getText());
        //assertEquals("VA", driver.findElement(By.xpath("//tr[46]/td[2]")).getText());
    }
}
