package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.WebDriver;

public interface ResultExtractor<R> {

	public abstract R generate(WebDriver driver);

}