package com.next.c1.i18n;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleMessageUtil 
{
	@Autowired
	ResourceLoader resourceLoader; 

	ReloadableResourceBundleMessageSource empty;
	@PostConstruct
	public void init()
	{
	}
	private ConcurrentHashMap<String,MessageSourceImpl> messageSources = new ConcurrentHashMap<String,MessageSourceImpl>();
	public MessageSource getMessageSource(String url)
	{
		MessageSourceImpl messageSource = messageSources.get(url);
		if(messageSource==null)
		{			
			messageSource = load(url);
			messageSources.put(url, messageSource);
		}
		return messageSource;
	}
	private MessageSourceImpl load(String url) {
		MessageSourceImpl rt = new MessageSourceImpl(url);
		return rt;
	}

}
