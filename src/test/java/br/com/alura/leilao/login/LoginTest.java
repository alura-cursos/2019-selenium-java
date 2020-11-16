package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_LANCES = "http://localhost:8080/leiloes/2";

	private WebDriver browser;

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
	}

	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		browser.navigate().to(URL_LOGIN);

		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		String nomeUsuarioLogado = browser.findElement(By.id("usuario-logado")).getText();
		Assert.assertEquals("fulano", nomeUsuarioLogado);
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		browser.navigate().to(URL_LOGIN);

		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
		Assert.assertTrue(browser.getCurrentUrl().contains(URL_LOGIN));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
	}

	@Test
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		browser.navigate().to(URL_LANCES);

		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
	}

}
