package org.jamescarr.jsspec.runner.webdriver;

public class JSSpecResult {

	private int examples;
	private int failures;
	private int errors;

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

	private int progress;
	private float elapsed;
	
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
