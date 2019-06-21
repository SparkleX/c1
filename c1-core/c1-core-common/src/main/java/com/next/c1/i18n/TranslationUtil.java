package com.next.c1.i18n;

import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TranslationUtil 
{
	final public static String NoTranslation = "(---)";
	public static void main(String[]argc)
	{
		new TranslationUtil().test();
	}
	
	void test()
	{

		String content = "aac\"{i18n>aZ.a}\"bbb\naac\"{i18n>bbb}\"bbb";  
		TranslationUtil util = new TranslationUtil();
		Properties props = new Properties();
		//props.put("aZ.a", "1");
		props.put("bbb", "2");
		String out =util.translate(content, "");
		System.out.println(out);
	}
	@PostConstruct
	private void init()
	{
		pattern = Pattern.compile("\\{i18n#([a-zA-Z_0-9.\\-/]*)\\}");  
	}
	@Autowired
	ResourceBundleMessageUtil resourceBundle;
	Pattern pattern;
	public String translate(String content, String url)
	{        
		url = "/" + url;
		url = FilenameUtils.removeExtension(url);
        Locale locale = LocaleContextHolder.getLocale();
		MessageSource messageSource = resourceBundle.getMessageSource(url);
		/*if(messageSource==null)
		{
			return content;
		}*/
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while(matcher.find())
        {
       		String key = matcher.group(1);
       		Object value = messageSource.getMessage(key, null, NoTranslation,locale);
       		if(value!=null)
       		{
       			String xmlValue = StringEscapeUtils.escapeXml11((String)value);
       			matcher.appendReplacement(sb, xmlValue);
       		}
        }
        matcher.appendTail(sb);
		return sb.toString();
	}
	
	public String translateTable(String content, String urlTable)
	{        
		Locale locale = LocaleContextHolder.getLocale();
		urlTable = "/" + urlTable;
		urlTable = FilenameUtils.removeExtension(urlTable);
		String urlTableExtend = urlTable.replaceAll("table", "tableExtend");
        
		MessageSource messageSource = resourceBundle.getMessageSource(urlTable);

		MessageSource messageSourceTableExtend = resourceBundle.getMessageSource(urlTableExtend);

        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while(matcher.find())
        {
       		String key = matcher.group(1);
       		Object value = messageSource.getMessage(key, null, null, locale);
       		if(value==null)
       		{
   				value = messageSourceTableExtend.getMessage(key, null, null,locale);
       		}
       		if(value==null) {
       			value = NoTranslation;
       		}
   			String xmlValue = StringEscapeUtils.escapeXml11((String)value);
   			matcher.appendReplacement(sb, xmlValue);
        }
        matcher.appendTail(sb);
		return sb.toString();
	}	
}
