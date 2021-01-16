package org.example.annotation;

import org.example.context.CsvFieldTypeContext;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CsvHandle {

    String fieldName() default "";

    /**
     * 默认类型为String
     * @see CsvFieldTypeContext
     */
    int fieldType() default 0;

}
