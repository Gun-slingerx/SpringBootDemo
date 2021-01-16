package org.example.model;

import lombok.Data;
import org.example.annotation.CsvHandle;
import org.example.context.CsvFieldTypeContext;

import java.util.Date;

/**
 * @description:
 * @author:Lr
 * @createTime:2021/1/12 9:00
 * @see:org.example.model
 */
@Data
public class From {

    @CsvHandle(fieldName = "strTo")
    private String strFromA;

    @CsvHandle(fieldName = "intTo" , fieldType = CsvFieldTypeContext.INT)
    private String strFromB;

//    @CsvHandle(fieldName = "date" , fieldType = CsvFieldTypeContext.DATE)
    private Date date;
}
