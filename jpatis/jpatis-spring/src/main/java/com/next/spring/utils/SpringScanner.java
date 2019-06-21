package com.next.spring.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;



final public class SpringScanner
{
	@SafeVarargs
	static public List<String> scanIncInterface(String scanPackage, Class<? extends Annotation> ... classes) 
	{
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(){
		    @Override
		    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		        return true;
		    }
		};

		return scan(provider, scanPackage, classes);
	}
	@SafeVarargs
	static public List<String> scan(String scanPackage, Class<? extends Annotation> ... classes) 
	{
	    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		return scan(provider, scanPackage, classes);
	}    
	@SafeVarargs
	static private List<String> scan(ClassPathScanningCandidateComponentProvider provider, String scanPackage, Class<? extends Annotation> ... classes)
	{
	    for(Class<? extends Annotation> clazz:classes)
	    {
	    	provider.addIncludeFilter(new AnnotationTypeFilter((Class<? extends Annotation>) clazz));
	    }
	    List<String> rt  =new ArrayList<String>();
	    for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) 
	    {
	    	String beanClassName = beanDef.getBeanClassName();
	    	rt.add(beanClassName);
	    }
		return rt;
	}
	
}
