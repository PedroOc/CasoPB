import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public enum TipoDriver{CHROME, FIREFOX};
    public static TipoDriver tipoDriverAtivo = TipoDriver.CHROME;
    private static WebDriver driver;
    private static String caminhoChromeDriver = "C:/drivers/chromedriver.exe";
    private static String caminhoGeckoDriver = "C:/drivers/geckodriver.exe";

    private DriverFactory() {}

    public static WebDriver retornaDriver(){
        if (driver == null){
            System.setProperty("webdriver.chrome.driver", caminhoChromeDriver);
            System.setProperty("webdriver.gecko.driver", caminhoGeckoDriver);
            switch (tipoDriverAtivo) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
            }

        }
        return  driver;
    }

    public static void matarDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}

