package org.jamescarr.jsspec.runner.webdriver;

import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class TotalMetricBuilderTest {
	private WebDriver driver;
	private TotalMetricBuilder builder;
	private ExampleGroupBuilder groupBuilder;
	@Before
	public void before(){
		driver = mock(WebDriver.class);
		groupBuilder = mock(ExampleGroupBuilder.class);
		
		builder = new TotalMetricBuilder(groupBuilder);
		
		
		defaults();
		
	}
	private void defaults() {
		using(driver).id("total_examples").returnsText("-1");
		using(driver).id("total_failures").returnsText("-1");
		using(driver).id("total_errors").returnsText("-1");
		using(driver).id("progress").returnsText("-1");
		using(driver).id("total_elapsed").returnsText("-1.21");
	}
	@Test
	public void shouldPassExampleGroupNodesToExampleGroupBuilder(){
		List<WebElement> elements = webElements("li", "success", 2);
		when(driver.findElements(By.xpath("//ul[@class='specs']/li"))).thenReturn(elements);
		
		builder.generate(driver);
		
		verify(driver).findElements(By.xpath("//ul[@class='specs']/li"));
	}
	
	@Test
	public void shouldGenerateSameNumberOfGroupsAsThereAreSpecLiNodes(){
		List<WebElement> elements = webElements("li", "success", 4);
		when(driver.findElements(By.xpath("//ul[@class='specs']/li"))).thenReturn(elements);
		
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(elements.size(), result.getExampleGroups().size());
	}
	@Test
	public void shouldCollectTotalNumberOfExamples(){
		using(driver).id("total_examples").returnsText("45");
	
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(45, result.getExamples());
	}
	@Test
	public void shouldCollectTotalFailures(){
		using(driver).id("total_failures").returnsText("40");
	
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(40, result.getFailures());
	}
	
	@Test
	public void shouldCollectTotalErrorsReported(){
		using(driver).id("total_errors").returnsText("63");
	
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(63, result.getErrors());
	}
	@Test
	public void shouldCollectProgress(){
		using(driver).id("progress").returnsText("89");
	
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(89, result.getProgress());
	}
	@Test
	public void shouldCollectTheTimeElapsed(){
		using(driver).id("total_elapsed").returnsText("2.389");
		
		JSSpecResult result = builder.generate(driver);
		
		assertEquals(2.389, result.getElapsed(), 3);
	}
		
	
}
