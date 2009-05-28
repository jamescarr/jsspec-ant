package org.jamescarr.jsspec.runner.webdriver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.jamescarr.jsspec.runner.ExampleBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TotalMetricBuilder implements ResultExtractor<JSSpecResult> {
	private final static Map<String, String> metrics = new LinkedHashMap<String, String>();
	static{
			metrics.put("total_examples", "examples");
			metrics.put("total_failures", "failures");
			metrics.put("total_errors", "errors");
			metrics.put("progress", "progress");
			metrics.put("total_elapsed", "elapsed");
	};
	
	private final ExampleGroupBuilder groupBuilder;
	
	public TotalMetricBuilder() {
		this.groupBuilder = new ExampleGroupBuilder(new ExampleBuilder());
		
	}
	public TotalMetricBuilder(ExampleGroupBuilder groupBuilder) {
		this.groupBuilder = groupBuilder;
		
	}
	public JSSpecResult generate(WebDriver driver) {
		JSSpecResult result = new JSSpecResult();
		
		for(Entry<String, String> entry : metrics.entrySet()){
			String value = driver.findElement(By.id(entry.getKey())).getText();
			assignValueToField(result, entry, value);
		}
		
		for(WebElement el : driver.findElements(By.xpath("//ul[@class='specs']/li"))){
			result.add(groupBuilder.generate(el));
		}
		
		return result;
	}
	private void assignValueToField(JSSpecResult result,
			Entry<String, String> entry, String value) {
		try {
			BeanUtils.setProperty(result, entry.getValue(), value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

}
