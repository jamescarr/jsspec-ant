package org.jamescarr.jsspec.runner;

import org.jamescarr.jsspec.runner.webdriver.Example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExampleBuilder  {

	public Example generate(WebElement element) {
		Example example = new Example();
		example.setName(element.findElement(By.tagName("h4")).getText());
		example.setStatus("success".equals(element.getAttribute("class")));
		return example;
	}

}
