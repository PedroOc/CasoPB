import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

public class Teste_Seletores {

    static WebDriver driver;
    static String caminhoChromeDriver = "C:/Users/pedro.octavio/OneDrive - Linx SA/Documentos/Automacao/drivers/chromedriver.exe";
    static WebDriverWait esperaDriver;

    @BeforeClass
    static public void antesDaClasse() throws Exception{
        System.setProperty("webdriver.chrome.driver", caminhoChromeDriver);
        driver = new ChromeDriver() ;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        esperaDriver = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void clicar3Botoes() throws Exception{
        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        String xpathBtnBar = "//*[@class='button']";
        String xpathBtnFoo = "//*[@class='button success']";

        esperaDriver.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBtnBar)));
        driver.findElement(By.xpath(xpathBtnBar)).click();
        driver.findElement(By.xpath("//*[@class='button alert']")).click();
        driver.findElement(By.xpath(xpathBtnFoo)).click();
    }

    @Test
    public void clicarTodosEditDelete() throws  Exception{
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        List<WebElement> linksEdit = driver.findElements(By.xpath("//a[@href='#edit'][contains(text(),'edit')]"));
        //System.out.println("número de edit's "+linksEdit.size());
        List<WebElement> linksDelete = driver.findElements(By.xpath("//a[@href='#delete'][contains(text(),'delete')]"));
        //System.out.println("número de delete's "+linksDelete.size());
        esperaDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#edit'][contains(text(),'edit')]")));
        for (WebElement linkEdit : linksEdit) {
            linkEdit.click();
            //System.out.println(driver.getCurrentUrl());
        }
        for (WebElement linkDelete : linksDelete) {
            linkDelete.click();
            //System.out.println(driver.getCurrentUrl());
        }
    }


    @AfterClass
    public static void BrowserClose(){
        driver.quit();
    }

}
