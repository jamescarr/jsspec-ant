package org.jamescarr.jsspec.runner.webdriver;

import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.using;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;

public class SpecRunnerTest {
	private WebDriver driver;
	private SpecRunner runner;
	private ResultExtractor extractor;
	private Thread t ;
	@Before
	public void before(){
		driver = mock(WebDriver.class);
		Navigation nav = mock(Navigation.class);
		when(driver.navigate()).thenReturn(nav);
		
		runner = new SpecRunner(driver);
		using(driver).id("progress").returnsText("100");
		
		extractor = mock(ResultExtractor.class);
		runner.setMetricExtractor(extractor);
	}
	@After
	public void after(){
		if(t != null && t.isAlive()){t.interrupt();}
	}
	public void run(Runnable runner){
			t = new Thread(runner);
			t.start();
	}
	@Test
	public void shouldCompleteWhenProgressHasReachedOneHundred() throws InterruptedException{
		using(driver).id("progress").returnsText("50");
		
		runner = new SpecRunner(driver);
		run(new Runnable(){
			public void run() {
				runner.execute("some-spec.html");
			}
		});
		
		Thread.sleep(500);
		assertTrue(runner.isRunning());
		
		using(driver).id("progress").returnsText("100");
		Thread.sleep(500);
		
		assertFalse(runner.isRunning());
	}
	@Test
	public void shouldNavigateToSpecifiedUrl(){	
		runner.execute("file:///C|/foo/somefile.html");
		
		verify(driver.navigate()).to("file:///C|/foo/somefile.html");
	}
	
	@Test
	public void shouldUseMetricExtractorToExtractMetrics(){
		JSSpecResult expectedResult = new JSSpecResult();
		when(extractor.generate(driver)).thenReturn(expectedResult);
		
		JSSpecResult result = runner.execute("file:///C|/foo/somefile.html");
	
		assertSame(result, expectedResult);
	}
	
}
