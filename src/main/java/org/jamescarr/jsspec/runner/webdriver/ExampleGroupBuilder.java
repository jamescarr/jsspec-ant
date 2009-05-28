package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExampleGroupBuilder {

	public ExampleGroupBuilder(ExampleBuilder exampleBuilder) {
		this.exampleBuilder = exampleBuilder;
	}

	private ExampleBuilder exampleBuilder;

	public ExampleGroup generate(WebElement el) {
		ExampleGroup group = new ExampleGroup();
		group.setTitle(el.findElement(By.tagName("h3")).getText());
		group.setPassing(el.getAttribute("class").equals("success"));
		
		for(WebElement example:el.findElements(By.xpath("ul[@class='examples']/li"))){
			group.add(exampleBuilder.generate(example));
		}
		return group;
	}

}
