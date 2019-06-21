package com.next.c1.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil 
{
	@Autowired
	ResourceBundleMessageUtil messageBundle;
	public String getMessage(MessageId strCode)
	{
		String url = "/stringList/" + strCode.getClass().getSimpleName()+".stringList";
		MessageSource messageSource = messageBundle.getMessageSource(url);
		Locale locale = LocaleContextHolder.getLocale();		
		String code = strCode.toString();
		String rt = messageSource.getMessage(code, null,"(---)", locale);
		return rt;
	}
}
