package org.example.handle.impl;

import org.example.context.CsvFieldTypeContext;
import org.example.handle.CsvHandle;
import org.example.model.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author admin
 */
public class CsvHandleImpl implements CsvHandle {

    @Override
    public <T> T csvHandleMapping(Object original, Class<T> systemClazz) {
        Class originalClazz = original.getClass();
        Field[] originalFields = originalClazz.getDeclaredFields();
        Map<String, FieldInfo> map = new HashMap<String, FieldInfo>();
        for (Field field : originalFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.example.annotation.CsvHandle.class)) {
                //获取注解值
                org.example.annotation.CsvHandle csvHandle = field.getAnnotation(org.example.annotation.CsvHandle.class);
                String fieldName = csvHandle.fieldName();
                int fieldType = csvHandle.fieldType();
                try {
                    Object value = field.get(original);
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setType(fieldType);
                    fieldInfo.setValue(value);
                    map.put(fieldName, fieldInfo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //根据system构造新的对象
        Field[] systemFields = systemClazz.getDeclaredFields();
        Set<String> fieldNames = map.keySet();
        try {
            T system = systemClazz.newInstance();
            for (Field field : systemFields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldNames.contains(fieldName)) {
                    continue;
                }
                String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = null;

                try {
                    FieldInfo fieldInfo = map.get(fieldName);
                    int type = fieldInfo.getType();
                    Object value = fieldInfo.getValue();
                    //根据类型和值设置构造system对象
                    switch (type) {
                        case CsvFieldTypeContext.STRING:
                            method = systemClazz.getDeclaredMethod(methodName, String.class);
                            String valueStr = String.valueOf(value);
                            method.invoke(system, valueStr);
                            break;
                        case CsvFieldTypeContext.INT:
                            method = systemClazz.getDeclaredMethod(methodName, int.class);
                            int valueInt = Integer.parseInt(String.valueOf(value));
                            method.invoke(system, valueInt);
                            break;
                        case CsvFieldTypeContext.DATE:
                            method = systemClazz.getDeclaredMethod(methodName, Date.class);
                            String timeStr = value.toString();
                            String origFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
                            //日期原始格式
                            timeStr = timeStr.replaceAll(":[^:]*$", "00");
                            System.out.println(timeStr);
                            DateFormat format = new SimpleDateFormat(origFormat);
                            try {
                                Date date = format.parse(timeStr);
                                String dateString = format.format(date);
                                System.out.println(dateString);
                                method.invoke(system, date);
                            } catch (ParseException e) {
                                System.out.println("日期类型转换异常");
                                e.printStackTrace();
                            }
                            break;
                        default:
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return system;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}

