package org.jamescarr.jsspec.runner.webdriver;

import java.util.ArrayList;
import java.util.List;

public class JSSpecResult {
	private int examples;
	private int failures;
	private int errors;	
	private int progress;
	private float elapsed;
	private List<ExampleGroup> exampleGroups= new ArrayList<ExampleGroup>();

	public List<ExampleGroup> getExampleGroups() {
		return exampleGroups;
	}

	public void add(ExampleGroup exampleGroup) {
		this.exampleGroups.add(exampleGroup);
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public float getElapsed() {
		return elapsed;
	}

	public void setElapsed(float elapsed) {
		this.elapsed = elapsed;
	}
	
	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	public int getExamples() {
		return examples;
	}

	public void setExamples(int examples) {
		this.examples = examples;
		
	}

	

}
