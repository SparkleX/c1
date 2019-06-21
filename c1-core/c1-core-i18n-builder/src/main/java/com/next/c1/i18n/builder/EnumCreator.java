package com.next.c1.i18n.builder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Component
public class EnumCreator {
	@Autowired
	CommandLine cmdLine;
	private Template template;
	@PostConstruct
	void init() throws IOException
	{
		this.loadTemplate();
	}

	static Logger logger = LoggerFactory.getLogger(EnumCreator.class);
	public void execute() throws IOException, TemplateException
	{
		File inputFolder  =new File(cmdLine.getInput());

		Collection<File> files = FileUtils.listFiles(inputFolder, FileFilterUtils.suffixFileFilter(".properties"), FileFilterUtils.trueFileFilter());
		for(File file:files)
		{
			String fileName = file.getName();
			String className = fileName.split("\\.")[0];

			Properties pps = new Properties();
			logger.info(fileName);
			InputStream in = new BufferedInputStream (new FileInputStream(file));  
			pps.load(in);
			in.close();
			
			StringWriter sw = new StringWriter();
			Map<String,Object> data =new HashMap<>();
			data.put("className", className);
			data.put("keys", pps.keys());
			this.template.process(data, sw);
			String outputFile = cmdLine.getOutput() + "/java/com/next/c1/resource/string/" + className + ".java";
			FileUtils.writeStringToFile(new File(outputFile), sw.toString(), "utf8");

		}
	}
	
	private void loadTemplate() throws IOException
	{
		URL u = ResourceUtils.getURL("classpath:template/MessageEnum.ftl");
		InputStream is = u.openStream();
		String content = IOUtils.toString(is, "utf8");
		is.close();
	
		Configuration cfg = new Configuration(new Version(2, 3, 20));
		StringTemplateLoader dummyLoader = new StringTemplateLoader();
		dummyLoader.putTemplate("default", content);
		cfg.setTemplateLoader(dummyLoader);
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		this.template = cfg.getTemplate("default");
	}
}
