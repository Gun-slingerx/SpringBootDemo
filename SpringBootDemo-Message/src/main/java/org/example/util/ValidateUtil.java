package org.example.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author yongSen.wang
 * @date 2020/6/15 16:24
 */
@Slf4j
public class ValidateUtil {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> void validate(T t) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(t);
        //将所有的校验失败的字段都打印出来
        set.forEach(o -> {
            log.error(
                "the BO : {}, validate fail, because of propert : {} has invalid value: {}, contraint : {} ",
                t.getClass(), o.getPropertyPath(), o.getInvalidValue(), o.getMessage());
        });

        //抛出一条错误信息给上游知晓
        set.forEach(o -> {
            throw new RuntimeException("参数校验失败：" + o.getMessage());
        });
    }
}
