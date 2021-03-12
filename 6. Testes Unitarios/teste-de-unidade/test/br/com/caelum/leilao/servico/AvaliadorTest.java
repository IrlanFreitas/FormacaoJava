package br.com.caelum.leilao.servico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao, jose, maria;

	@Before
	public void criaCenario() {
		this.leiloeiro = new Avaliador();

		joao = new Usuario("Jo�o");
		jose = new Usuario("Jos�");
		maria = new Usuario("Maria");
	}

	@After
	public void finaliza() {
		System.out.println("fim");
	}

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}
	
	//Indica que ficar� no aguardo do erro
	//pela classe RuntimeException
	//n�o sendo mais necess�rio
	//o uso do try catch
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {

		Leilao leilao = new CriadorDeLeilao().para("Nintendo Switch").constroi();

		leiloeiro.avalia(leilao);
		Assert.fail();

	}

	@Test
	// M�todo tem que ser publico e n�o receber param�tro
	public void deveEntenderLancesEmOrdemCrescente() {
		// Parte 1: Cen�rio
//		Usuario joao = new Usuario("Jo�o");
//		Usuario jose = new Usuario("Jos�");
//		Usuario maria = new Usuario("Maria");

		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo").lance(joao, 250.0).lance(jose, 300.0)
				.lance(maria, 400.0).constroi();

		// Parte 2: A��o
//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: Valida��o
		double maiorEsperado = 400;
		double menorEsperado = 250;
		double mediaEsperado = 316.67;
//
//		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
//		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
//		assertEquals(mediaEsperado, leiloeiro.getValorMedio(), 0.00001);
		
		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(250.0));

	}

	@Test
	// M�todo tem que ser publico e n�o receber param�tro
	public void deveEntenderLancesEmOrdemDecrescente() {
		// Parte 1: Cen�rio
		Usuario joao = new Usuario("Jo�o");
		Usuario jose = new Usuario("Jos�");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		// Lance repetido, ser� ignorado
		leilao.propoe(new Lance(maria, 100.0));

		// Parte 2: A��o
//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: Valida��o
		double maiorEsperado = 400;
		double menorEsperado = 200;
		double mediaEsperado = 300;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(mediaEsperado, leiloeiro.getValorMedio(), 0.00001);

	}

	@Test
	// M�todo tem que ser publico e n�o receber param�tro
	public void deveEntenderLancesEmOrdemAleatoria() {
		// Parte 1: Cen�rio
		Usuario joao = new Usuario("Jo�o");
		Usuario jose = new Usuario("Jos�");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo").lance(joao, 200.0).lance(jose, 450.0)
				.lance(maria, 120.0).lance(joao, 630.0).lance(jose, 700.0).lance(maria, 230.0).constroi();

		// Parte 2: A��o
//		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: Valida��o
		double maiorEsperado = 700;
		double menorEsperado = 120;
		double mediaEsperado = 388.33;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(mediaEsperado, leiloeiro.getValorMedio(), 0.00001);

	}
	
	@Ignore
	@Test
	public void testaMediaDeZeroLance() {
		// cenario
//        Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");

//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// validacao
		assertEquals(0, leiloeiro.getValorMedio(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		// Parte 1: Cen�rio
		Usuario joao = new Usuario("Jo�o");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 1000.0));

		// Parte 2: A��o
//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: Valida��o
		double maiorEsperado = 1000;
		double menorEsperado = 1000;
		double mediaEsperado = 1000;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(mediaEsperado, leiloeiro.getValorMedio(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLancesComTresLances() {

		// Cen�rio
		Usuario joao = new Usuario("Jo�o");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 5 Novo");

		// A��o
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Valida��o
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();

		assertEquals(3, tresMaiores.size());
		assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.00001);

	}

	@Test
	public void deveEncontrarOsTresMaioresLancesComCincoLances() {

		// Cen�rio
		Usuario joao = new Usuario("Jo�o");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 5 Novo");

		// A��o
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		// Lance repetido, ser� ignorado
		leilao.propoe(new Lance(maria, 500.0));

//		criaAvaliador
		leiloeiro.avalia(leilao);

		// Valida��o
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();

		assertEquals(3, tresMaiores.size());
		
		assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.00001);

	}

	@Test
	public void deveEncontrarOsTresMaioresLancesComDoisLances() {

		// Cen�rio
		Usuario joao = new Usuario("Jo�o");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 5 Novo");

		// A��o
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));

//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Valida��o
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();

		assertEquals(2, tresMaiores.size());
		assertEquals(200, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(100, tresMaiores.get(1).getValor(), 0.00001);
	}
	
	@Ignore
	@Test
	public void deveEncontrarOsTresMaioresLancesSemLances() {

		// Cen�rio
		Leilao leilao = new Leilao("Playstation 5 Novo");

		// A��o

//		criaAvaliador();
		leiloeiro.avalia(leilao);

		// Valida��o
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();

		assertEquals(0, tresMaiores.size());
	}
}
