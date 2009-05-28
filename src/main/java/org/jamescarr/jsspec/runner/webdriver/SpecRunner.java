package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpecRunner {

	private final WebDriver driver;

	public SpecRunner(WebDriver driver) {
		this.driver = driver;
	}

	public JSSpecResult execute(String specName) {
		driver.navigate().to(specName);
		while(!driver.findElement(By.id("progress")).getText().equals("100")){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return createResult();
	}

	private JSSpecResult createResult() {
		JSSpecResult specResult = new JSSpecResult();
		specResult.setExamples(Integer.parseInt(driver.findElement(By.id("total_examples")).getText()));
		return specResult;
	}
}
