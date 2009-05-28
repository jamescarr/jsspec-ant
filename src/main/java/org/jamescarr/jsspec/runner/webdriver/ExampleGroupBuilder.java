package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExampleGroupBuilder {

	public ExampleGroup generate(WebElement el) {
		ExampleGroup group = new ExampleGroup();
		group.setTitle(el.findElement(By.tagName("h3")).getText());
		group.setPassing(el.getAttribute("class").equals("success"));
		return group;
	}

}
