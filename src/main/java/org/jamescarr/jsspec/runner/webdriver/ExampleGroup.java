package org.jamescarr.jsspec.runner.webdriver;

import java.util.ArrayList;
import java.util.List;

public class ExampleGroup {
	private String title;
	private List<Example> examples = new ArrayList<Example>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void add(Example example){
		examples.add(example);
	}
	public List<Example> getExamples() {
		return examples;
	}
	
}
