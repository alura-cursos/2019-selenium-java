package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;

	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}

	@Test
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		this.paginaDeLeiloes = paginaDeLogin.efetuarLogin("fulano", "pass");
		CadastroLeilaoPage paginaDeCadastroDeLeilao = paginaDeLeiloes.carregarFormulario();

		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nomeLeilao = "Leilao do dia " + hoje;
		String valorInicial = "500.00";

		this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao(nomeLeilao, valorInicial, hoje);

		Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nomeLeilao, valorInicial, hoje));
	}

}
