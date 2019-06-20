package com.next.jpatis.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@QueryAnnotation
@Documented
@Query
public @interface SQL {
	@AliasFor(annotation = Query.class)
	String value() default "";
	@AliasFor(annotation = Query.class)
	String countQuery() default "";
	@AliasFor(annotation = Query.class)
	String countProjection() default "";
	@AliasFor(annotation = Query.class)
	boolean nativeQuery() default true;
	@AliasFor(annotation = Query.class)
	String name() default "";
	@AliasFor(annotation = Query.class)
	String countName() default "";
}