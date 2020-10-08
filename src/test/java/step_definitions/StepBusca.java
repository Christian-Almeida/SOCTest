package step_definitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class StepBusca {


	 WebDriver driver;
	@Test
	@Dado("que o usuário esteja na página de blog do site")
	public void testContexto() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://ww2.soc.com.br/blog/");
		driver.manage().window().maximize();
		new WebDriverWait(driver,20).until((ExpectedConditions.titleIs("Conteúdos SOC - Medicina e Segurança do Trabalho")));
		new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.id("barra-cookie"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	@Quando("o usuario realiza uma pesquisa no campo de busca")
	public void testAcao() {
		WebElement busca = driver.findElement(By.xpath("//*[@id=\"blog\"]/div/div[1]/div/form/input[3]"));
		busca.sendKeys("Novidades");

		WebElement botaoClique = driver.findElement(By.cssSelector("form[class='form-busca']")).findElement((By.cssSelector("input[class='lupa-form']")));
		botaoClique.click();
	}

	@Test
	@Entao("deve ver o texto {string}")
	public void testResultado(String msg) {

		String texto = driver.findElement(By.cssSelector("h2.pagetitle")).getText();
		String[] splitText = texto.split(":");
		Assert.assertEquals(msg, splitText[0]);
		driver.quit();
	}

}


