package com.next.c1.i18n.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommandLine {
	public String getInput() {
		return input;
	}
	public String getOutput() {
		return output;
	}
	@Value("${input:../../c1-resources/src/main/resources/string}")
	String input;
	@Value("${output:../../c1-resources/target/generated-sources}")
	String output;
}
