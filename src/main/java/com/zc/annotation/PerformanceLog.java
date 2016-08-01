package com.zc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })

public @interface PerformanceLog {
	 String RequestClass() default "";
	 String RequestMethod() default "";
	 String ResponseClass() default "";
	 String ResponseMethod() default "";
}

