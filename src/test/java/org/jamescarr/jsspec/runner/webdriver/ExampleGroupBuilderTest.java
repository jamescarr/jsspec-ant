package org.jamescarr.jsspec.runner.webdriver;

import org.junit.Test;
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

public class ExampleGroupBuilderTest {
	private WebElement el;
	private ExampleBuilder builder = new ExampleBuilder();
	@Before
	public void before(){
		this.el = mock(WebElement.class);
		using(el).tag("h3").returnsText("default");
	}

	@Test
	public void shouldGetNameFromH3Element(){
		using(el).tag("h3").returnsText("Example Group Description");
		
		ExampleGroup group = new ExampleGroupBuilder().generate(el);
	}
}
