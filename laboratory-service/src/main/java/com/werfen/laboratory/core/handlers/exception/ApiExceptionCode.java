package com.werfen.laboratory.core.handlers.exception;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiExceptionCode {
    String value() default "";

    String text() default "";
}