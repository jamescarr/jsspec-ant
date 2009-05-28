package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExampleBuilder  {

	public Example generate(WebElement element) {
		Example example = new Example();
		example.setName(element.findElement(By.tagName("h4")).getText());
		return example;
	}

}
