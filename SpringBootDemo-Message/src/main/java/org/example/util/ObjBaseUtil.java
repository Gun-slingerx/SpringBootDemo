package org.example.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author LR
 * @version 1.0
 * @date 2020/10/16 10:24
 * @Description
 */
public class ObjBaseUtil {

    /**
     * @Author Lr
     * @Date 2020/10/16 10:24
     * @Description 获取对象中为空的字段并赋初始值
     */
    public static Object checkObjectFieldValue(Object obj) {
        if (obj != null) {
            Class<? extends Object> classz = obj.getClass();
            Field fields[] = classz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    Type t = field.getGenericType();
                    if (t.equals(String.class)) {
                        Method m = classz.getMethod("get"
                                + change(field.getName()));
                        Object name = m.invoke(obj);
                        if (name == null) {
                            Method mtd = classz.getMethod(
                                    "set" + change(field.getName()),
                                    new Class[]{String.class});
                            mtd.invoke(obj, new Object[]{""});
                        }
                    }
                    if (t.equals(Long.class)) {
                        Method m = classz.getMethod("get"
                                + change(field.getName()));
                        Object name = m.invoke(obj);
                        if (name == null) {
                            Method mtd = classz.getMethod(
                                    "set" + change(field.getName()),
                                    new Class[]{Long.class});
                            mtd.invoke(obj, new Object[]{0l});
                        }
                    }
                    if (t.equals(Integer.class)) {
                        Method m = classz.getMethod("get"
                                + change(field.getName()));
                        Object name = m.invoke(obj);
                        if (name == null) {
                            Method mtd = classz.getMethod(
                                    "set" + change(field.getName()),
                                    new Class[]{Integer.class});
                            mtd.invoke(obj, new Object[]{0});
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * @param str 源字符串
     * @return
     */
    private static String change(String str) {
        if (str != null) {
            StringBuffer sb = new StringBuffer(str);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }
}
