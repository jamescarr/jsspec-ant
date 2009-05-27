package org.jamescarr.jsspec.runner.webdriver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



import static org.mockito.Mockito.*;

public class SpecRunnerTest {
	private WebDriver driver;
	private SpecRunner runner;
	@Before
	public void before(){
		driver = mock(WebDriver.class);
		Navigation nav = mock(Navigation.class);
		when(driver.navigate()).thenReturn(nav);
		
		runner = new SpecRunner(driver);
	}
	@Test
	public void shouldNavigateToSpecifiedUrl(){	
		runner.execute("file:///C|/foo/somefile.html");
		
		verify(driver.navigate()).to("file:///C|/foo/somefile.html");
	}
	@Test
	public void shouldCollectExecutionMetrics(){
		runner = new SpecRunner(new HtmlUnitDriver(true));
		JSSpectResult result = runner.execute("http://jania.pe.kr/jsspec/demo.html");
	}
}
