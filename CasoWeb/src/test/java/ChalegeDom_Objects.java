import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChalegeDom_Objects {
    static WebDriver driver;
    static WebDriverWait esperaDriver;

    public ChalegeDom_Objects(){
        driver = DriverFactory.retornaDriver();
        esperaDriver = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private String xpathBotaoAzul = "//*[@class='button']";

    public void acessarPaginaChalengeDom(){
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
    }

    public void esperarBotaoAzul(){
        esperaDriver.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBotaoAzul)));
    }

    public void clicarBotaoAzul(){
        driver.findElement(By.xpath(xpathBotaoAzul)).click();
    }

    public void clicarBotaoVermelho(){
        driver.findElement(By.xpath("//*[@class='button alert']")).click();
    }

    public void clivarBotaoVerde(){
        driver.findElement(By.xpath("//*[@class='button success']")).click();
    }
}
