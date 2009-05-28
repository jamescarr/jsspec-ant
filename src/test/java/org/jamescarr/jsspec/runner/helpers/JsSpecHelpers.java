package org.jamescarr.jsspec.runner.helpers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsSpecHelpers {
	

	public static WebElementMocker using(WebDriver driver) {
		return new WebElementMocker(driver);
	}

	public static WebElementMocker using(WebElement element) {
		return new WebElementMocker(element);
	}
	
	public static String localSpec(String jsspecName) {
		try {
			return new File("src/test/resources/" + jsspecName).toURL()
					.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
