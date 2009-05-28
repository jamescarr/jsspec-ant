package org.jamescarr.jsspec.runner;

import org.jamescarr.jsspec.runner.webdriver.Example;
import org.jamescarr.jsspec.runner.webdriver.ExampleGroup;
import org.jamescarr.jsspec.runner.webdriver.JSSpecResult;
import org.jamescarr.jsspec.runner.webdriver.SpecRunner;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.jamescarr.jsspec.runner.helpers.JsSpecHelpers.localSpec;
public class IntegrationTest {
	@Test
	public void shouldGetResults(){
		SpecRunner runner = new SpecRunner(new HtmlUnitDriver(true));
		JSSpecResult result = runner.execute(localSpec("demo.html"));
		
		System.out.println("EXAMPLES: " + result.getExamples());
		System.out.println("Errors: " + result.getErrors());
		System.out.println("FAIL: " + result.getFailures());
		System.out.println(result.getProgress()+"% complete");
		System.out.println("ELAPSED TIME: " + result.getElapsed() + " seconds");
		
		for(ExampleGroup grp : result.getExampleGroups()){
			System.out.println(grp.getTitle() + " "+ (grp.isPassing()?"pass":"fail"));
			for(Example ex : grp.getExamples()){
				System.out.println("\t"+ex.getName() + " "+ (ex.isStatus()?"pass":"fail"));
			}
		}
	}
}
