package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastroLeilaoPage extends PageObject {

	private static final String URL_FORM = "http://localhost:8080/leiloes/new";

	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);
	}

	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
		this.browser.findElement(By.id("nome")).sendKeys(nome);
		this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
		this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
		this.browser.findElement(By.id("button-submit")).click();

		return new LeiloesPage(browser);
	}

	public boolean isMensagensDeValidacaoVisiveis() {
		String pageSource = this.browser.getPageSource();
		return pageSource.contains("não deve estar em branco") && pageSource.contains("minimo 3 caracteres") && pageSource.contains("deve ser um valor maior de 0.1") && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}

	public boolean isPaginaAtual() {
		return this.browser.getCurrentUrl().equals(URL_FORM);
	}

}
