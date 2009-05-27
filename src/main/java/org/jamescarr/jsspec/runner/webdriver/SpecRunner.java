package org.jamescarr.jsspec.runner.webdriver;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public class SpecRunner {

	private final WebDriver driver;

	public SpecRunner(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(String specName) {
		driver.navigate().to(specName);
		
	}

}
