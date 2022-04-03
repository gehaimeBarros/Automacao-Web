package br.com.automacao;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * 
 * @author Gehaime B.
 *
 */
public class Page {

	String caminho = "C:\\JavaProjetos\\Automacao\\evidencias\\success";
	String tipoImagem = ".png";
	
	WebDriver web = new ChromeDriver();
	WebElement google;
	WebElement e;
	
	String imagem;
	String pesquisa = "";
	String pesquisaOption;

	@Test
	public void pessoa() throws InterruptedException {
		web.get("https://www.google.com");
		web.manage().window().maximize();
		google = web.findElement(By.xpath("//*[contains(@class, 'gLFyf gsfi')]"));
		pesquisaOption = JOptionPane.showInputDialog("Digite oque deseja buscar");
		google.sendKeys(pesquisaOption, Keys.ENTER);
		e = web.findElement(By.xpath("//*[text()='Imagens']"));
		e.click();
	}

	@After
	public void finalizado() {
		try {
			Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(web);
			ImageIO.write(s.getImage(), "PNG", new File(caminho + pesquisaOption + tipoImagem));

		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		Assert.assertNotNull(google);
		web.close();
		System.out.println("\n\n\t\t\t=======TESTE FINALIZADO=======");
	}

}
