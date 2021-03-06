package org.jamescarr.jsspec.runner;

import org.jamescarr.jsspec.runner.webdriver.Example;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExampleBuilderTest {
	private WebElement el;
	private ExampleBuilder builder = new ExampleBuilder();
	@Before
	public void before(){
		this.el = mock(WebElement.class);
		using(el).tag("h4").returnsText("default");
	}
	@Test
	public void shouldSetDescriptionUsingH4FromElement(){
		using(el).tag("h4").returnsText("This is the description");
		
		Example example = builder.generate(el);
		
		assertEquals("This is the description", example.getName());
	}
	
	@Test
	public void shouldFailWhenClassIsNotSuccess(){
		when(el.getAttribute("class")).thenReturn("failed");
		
		Example example = builder.generate(el);
		
		assertFalse(example.isStatus());
	}
	
	@Test
	public void shouldPassWhenClassIsSuccess(){
		when(el.getAttribute("class")).thenReturn("success");
		
		Example example = builder.generate(el);
		
		assertTrue(example.isStatus());
	}
}
