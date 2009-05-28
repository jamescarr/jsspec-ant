package org.jamescarr.jsspec.runner.webdriver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TotalMetricBuilder implements ResultExtractor<JSSpecResult> {
	private final static Map<String, String> metrics = new LinkedHashMap<String, String>(){
		private static final long serialVersionUID = 1L;

		{
			put("total_examples", "examples");
			put("total_failures", "failures");
			put("total_errors", "errors");
			put("progress", "progress");
			put("total_elapsed", "elapsed");
		}
	};
	public JSSpecResult generate(WebDriver driver) {
		JSSpecResult result = new JSSpecResult();
		
		for(Entry<String, String> entry : metrics.entrySet()){
			String value = driver.findElement(By.id(entry.getKey())).getText();
			assignValueToField(result, entry, value);
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
