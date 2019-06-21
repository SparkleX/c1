package com.next.c1.i18n;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.ResourceUtils;

public class MessageSourceImpl implements MessageSource {

	Properties dev;
	Properties en;
	ConcurrentHashMap<Locale, Properties> translation;
	private String url;

	public MessageSourceImpl(String url) {
		if(url.startsWith("/")) url = url.substring(1);
		this.url = url;
		translation = new ConcurrentHashMap<Locale, Properties>();
		this.en = this.loadResource(url, "_en_US");
		this.dev = this.loadResource(url, "");
	}

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		Properties prop;
		//If en_US, use development languages
		if(locale.equals(Locale.US)){
			prop = dev;
		}
		else{
			prop = loadLocale(locale);
		}
		
		String strForeign = prop.getProperty(code);
		if (strForeign == null) {
			return defaultMessage;
		}
		return strForeign;
	}

	private Properties loadLocale(Locale locale) {
		Properties prop = translation.get(locale);
		if (prop != null) {
			return prop;
		}
		String langCode = locale.toLanguageTag();
		langCode = langCode.replaceAll("-", "_");
		prop = loadResource(url, "_"+langCode);
		removeChanged(prop);
		translation.put(locale, prop);
		return prop;
	}
	private void removeChanged(Properties prop) {
		
		Enumeration<Object> iterKey = prop.keys();
		for(;iterKey.hasMoreElements();)
		{
			String key = (String) iterKey.nextElement();
			//If dev and en not same, it is changed
			String strEn = en.getProperty(key);
			String strDev = dev.getProperty(key);
			if(StringUtils.equals(strEn, strDev)==false) {
				prop.remove(key);
			}
		}
		
	}

	private Properties loadResource(String url, String langCode) {
		
		url = "classpath:" + url + langCode + ".properties";
		Properties prop = new Properties();
		try (InputStream is = ResourceUtils.getURL(url).openStream()) {
			prop = new Properties();
			prop.load(is);
		} catch (Exception ex) {
		}
		return prop;
	}
	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return this.getMessage(code, args, null, locale);
	}

	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		throw new UnsupportedOperationException();
	}

}
