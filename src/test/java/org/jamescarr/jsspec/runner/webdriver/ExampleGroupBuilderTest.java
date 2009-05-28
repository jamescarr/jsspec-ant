package org.jamescarr.jsspec.runner.webdriver;

import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.using;
import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.webElements;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExampleGroupBuilderTest {
	private WebElement el;
	private ExampleGroupBuilder builder = new ExampleGroupBuilder();
	private ExampleBuilder exampleBuilder;
	@Before
	public void before(){
		this.el = mock(WebElement.class);
		using(el).tag("h3").returnsText("default");
		when(el.getAttribute("class")).thenReturn("exception");
		
		
		exampleBuilder = mock(ExampleBuilder.class);
		builder.setExampleBuilder(exampleBuilder);
	}

	@Test
	public void shouldGetNameFromH3Element(){
		using(el).tag("h3").returnsText("Example Group Description");
		
		ExampleGroup group = builder.generate(el);
		
		assertEquals("Example Group Description",group.getTitle());
	}
	
	@Test
	public void shouldSetGroupPassToTrueIfClassIsSuccess(){
		when(el.getAttribute("class")).thenReturn("success");
		
		ExampleGroup group = builder.generate(el);
		
		assertTrue(group.isPassing());
	}
	
	@Test
	public void shouldSetGroupPassToFalseIfClassIsFailure(){
		when(el.getAttribute("class")).thenReturn("exception");
		
		ExampleGroup group = builder.generate(el);
		
		assertFalse(group.isPassing());
	}
	
	@Test
	public void shouldUseExampleBuilderToBuildEachExample(){
		List<WebElement> elements = createListOfExampleNodes(3);
		
		builder.generate(el);
		
		for(WebElement element:elements){
			verify(exampleBuilder).generate(element);
		}
	}

	private List<WebElement> createListOfExampleNodes(int numberOfElements) {
		List<WebElement> elements = webElements("li", "success", numberOfElements);
		when(el.findElements(By.xpath("ul[@class='examples']/li"))).thenReturn(elements);
		return elements;
	}
	
	@Test
	public void shouldHaveNumberOfExamplesCorrespondingToExampleNodes(){
		List<WebElement> elements = createListOfExampleNodes(5);
		
		ExampleGroup group = builder.generate(el);
		
		assertEquals(elements.size(), group.getExamples().size());
	}
}
