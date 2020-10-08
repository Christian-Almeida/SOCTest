package step_definitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;


public class StepFAP {

	WebDriver navegador;

	@Test
	@Dado("que esteja na página FAP")
	public void que_esteja_na_página_fap() {

		WebDriverManager.chromedriver().setup();
		navegador = new ChromeDriver();
		navegador.manage().window().maximize();
		navegador.get("https://ww2.soc.com.br/blog/");
		new WebDriverWait(navegador, 10).until((ExpectedConditions.titleIs("Conteúdos SOC - Medicina e Segurança do Trabalho")));
		new WebDriverWait(navegador, 10).until(ExpectedConditions.elementToBeClickable(By.id("barra-cookie"))).click();

		new WebDriverWait(navegador, 10)
				.until(ExpectedConditions.titleIs("Conteúdos SOC - Medicina e Segurança do Trabalho"));

		new Actions(navegador).moveToElement(new WebDriverWait(navegador, 20)
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//*[@id=\"navegacao-superior\"]/ul/li[3]"))))
				.build().perform();
		new WebDriverWait(navegador, 10)
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//*[@id=\"navegacao-superior\"]/ul/li[3]/div/div[2]/ul/li[4]/a")))
				.click();
	}

	@Test
	@Quando("preencher todos os campos do formulário")
	public void preencher_todos_os_campos_do_formulário() {

		WebDriverWait wait = new WebDriverWait(navegador, 10);
		new WebDriverWait(navegador, 10)
				.until(ExpectedConditions.titleIs("FAP - SOC - Software Integrado de Gestão Ocupacional"));


		//Campo Empresa
		WebElement empresa = wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("input[id=\"nomeEmpresa\"]")));
		empresa.sendKeys("Relikia Boutiq");


		//Campo FAP
		WebElement fap = wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("input[id=\"fap\"]")));
		fap.sendKeys("20");


		//Campo RAT
		WebElement ratButton = wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("b.button")));
		ratButton.click();

		new Actions(navegador).moveToElement(new WebDriverWait(navegador, 20)
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//*[@id=\"frmcalculadora\"]/div[2]/div[3]/span/div/div[3]/div/ul/li[4]"))));

		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//*[@id=\"frmcalculadora\"]/div[2]/div[3]/span/div/div[3]/div/ul/li[4]")))
				.click();


		//Campo projeção
		WebElement projecao = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='projecao'")));
		projecao.sendKeys("10000");
	}

	@Test
	@Quando("clicar no botão Estimar FAP")
	public void clicar_no_botão_estimar_fap() {

		WebDriverWait wait = new WebDriverWait(navegador, 10);
		WebElement botEstimar = wait.until(ExpectedConditions.elementToBeClickable(By.id("estimar")));
		botEstimar.click();

	}

	@Test
	@Entao("deve abrir uma nova página com a {string}")
	public void deve_abrir_uma_nova_página_com_a(String texto) {
		WebDriverWait wait = new WebDriverWait(navegador, 10);
		String textpage = navegador.findElement(By.cssSelector("header.header-relatorio")).getText();
		Assert.assertEquals(texto, textpage);

		navegador.quit();
	}
}

