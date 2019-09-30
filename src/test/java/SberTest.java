import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class SberTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        driver = new ChromeDriver();
        baseUrl = "\n" + "http://www.sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testInsurance() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("//ol[contains(@class,'http://www.sberbank.ru/ru/person')]/li/*[contains(text(),'Выбор региона')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Нижегородская область')]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//*[contains(text(),'Нижегородская область')][contains(@class,'btn')]"))));

        driver.findElement(By.xpath("//*[contains(text(),'Нижегородская область')][contains(@class,'btn')]")).click();
        String footerPath = "/html/body/div[1]/div[4]/div/div/div/div[3]/footer";
        WebElement element = driver.findElement(By.xpath(footerPath));
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        wait = new WebDriverWait(driver, 5, 1000);
        String soc = "/html/body/div[1]/div[4]/div/div/div/div[3]/footer/div/div[2]/div[3]/ul";
        WebElement social = driver.findElementByXPath(soc);
        System.out.println(social.isDisplayed());


    }
}