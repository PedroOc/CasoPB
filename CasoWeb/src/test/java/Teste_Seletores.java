import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Teste_Seletores {

    static private WebDriver driver;
    static private WebDriverWait esperaDriver;
    static private ChalegeDom_Objects paginaChalengeDom;

    @BeforeClass
    static public void antesDaClasse() throws Exception{
        driver = DriverFactory.retornaDriver();
        paginaChalengeDom = new ChalegeDom_Objects();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        esperaDriver = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    @Test
    public void clicar3Botoes() throws Exception{
        paginaChalengeDom.acessarPaginaChalengeDom();
        paginaChalengeDom.esperarBotaoAzul();
        paginaChalengeDom.clicarBotaoAzul();
        paginaChalengeDom.clicarBotaoVermelho();
        paginaChalengeDom.clivarBotaoVerde();
    }

    @Test
    public void clicarTodosEditDelete() throws  Exception{
        paginaChalengeDom.acessarPaginaChalengeDom();
        List<WebElement> linksEdit = driver.findElements(By.xpath("//a[@href='#edit'][contains(text(),'edit')]"));
        List<WebElement> linksDelete = driver.findElements(By.xpath("//a[@href='#delete'][contains(text(),'delete')]"));
        esperaDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#edit'][contains(text(),'edit')]")));
        for (WebElement linkEdit : linksEdit) {
            System.out.println("número de edit's "+linksEdit.size());
            linkEdit.click();
            WebElement campoLorem = linkEdit.findElements(By.xpath("../../td")).get(0);
            System.out.println("Edit: Valor campo lorem "+campoLorem.getText());
            //System.out.println("Posição do edit na tela "+linkEdit.getLocation());
        }
        for (WebElement linkDelete : linksDelete) {
            System.out.println("número de delete's "+linksDelete.size());
            linkDelete.click();
            WebElement campoLorem = linkDelete.findElements(By.xpath("../../td")).get(0);
            System.out.println("Delete: Valor campo lorem "+campoLorem.getText());
            //System.out.println("Posição do delete na tela "+linkDelete.getLocation());
        }
    }


    @AfterClass
    public static void BrowserClose(){
        DriverFactory.matarDriver();
    }

}
