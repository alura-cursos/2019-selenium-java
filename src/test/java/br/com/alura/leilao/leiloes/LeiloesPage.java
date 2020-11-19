package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage {

	private static final String URL_FORM = "http://localhost:8080/leiloes/new";

	private WebDriver browser;

	public LeiloesPage(WebDriver browser) {
		this.browser = browser;
	}

	public void fechar() {
		this.browser.quit();
	}

	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_FORM);
		return new CadastroLeilaoPage(browser);
	}

	public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
		WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
		
		return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(dataAbertura) && colunaValorInicial.getText().equals(valorInicial);
	}

}
