package org.example.annotation;

import java.lang.annotation.*;

/**
 * @author LR
 * @version 1.0
 * @date 2020/10/22 10:23
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {

    /**
     * 延时时间 在延时多久后可以再次提交
     *
     * @return Time unit is one second
     */
    int delaySeconds() default 20;
}
