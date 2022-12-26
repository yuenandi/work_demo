package org.example.annotation.demo.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Format {
    String pattern() default "yyyy-MM-dd HH:MM:ss";
    String timezone() default "GMT+8";
}
