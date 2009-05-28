package org.jamescarr.jsspec.runner.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpecRunner {

	private final WebDriver driver;
	private boolean running = false;
	private ResultExtractor<JSSpecResult> extractor;

	public SpecRunner(WebDriver driver) {
		this(driver, new TotalMetricBuilder());
	}
	public SpecRunner(WebDriver driver, ResultExtractor<JSSpecResult> extractor) {
		this.driver = driver;
		this.extractor = extractor;
	}

	public JSSpecResult execute(String specName) {
		running = true;
		driver.navigate().to(specName);
		while(!driver.findElement(By.id("progress")).getText().equals("100")){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
			}
		}
		running = false;
		return createResult();
	}

	private JSSpecResult createResult() {
		return extractor.generate(driver);
	}

	public boolean isRunning() {
		return running;
	}
}
