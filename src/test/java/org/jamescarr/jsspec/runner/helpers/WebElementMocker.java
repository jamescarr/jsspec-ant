package org.jamescarr.jsspec.runner.helpers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementMocker {
	private WebDriver driver;
	private WebElement element;

	public class Returner {
		private String id;
		private String tag;

		public Returner tag(String tag) {
			this.tag = tag;
			return this;
		}

		public Returner id(String id) {
			this.id = id;
			return this;
		}

		public void returnsText(String text) {
			WebElement webElement = webElement(text);
			if(element == null){
				when(driver.findElement(getLocator())).thenReturn(webElement);
			}else{
				when(element.findElement(getLocator())).thenReturn(webElement);
			}
		}

		private By getLocator() {
			return (id!=null)?By.id(id):By.tagName(tag);
		}
	}

	public WebElementMocker(WebElement element) {
		super();
		this.element = element;
	}
	public WebElementMocker(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public Returner id(String id) {
		return new Returner().id(id);
	}

	public Returner tag(String tag) {
		return new Returner().tag(tag);
	}

	private WebElement webElement(String text) {
		WebElement fakeWebElement = mock(WebElement.class);
		when(fakeWebElement.getText()).thenReturn(text);
		return fakeWebElement;
	}
}
