package org.example.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LR
 * @version 1.0
 * @date 2020/10/13 14:34
 * @Description
 */
public class ObjConversionUtil {

    /**
     * @Author Lr
     * @Description objectToMap
     * @Date 2020/10/13 14:38
     * @Return
     */
    public static Map<String, Object> parseObj2Map(Object args) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(args.getClass()))
                .filter(pd -> !"class".equals(pd.getName()))
                .collect(HashMap::new,
                        (map, pd) -> map.put(pd.getName(), ReflectionUtils.invokeMethod(pd.getReadMethod(), args)),
                        HashMap::putAll);
    }

    /**
     * @Author Lr
     * @Description parseObj2MapByFieldName
     * @Date 2020/10/13 14:47
     * @Return
     */
    public static Map<String, String> parseObj2MapByFieldName(Object args, String[] fieldNames) {
        Map<String, Object> oldMap = parseObj2Map(args);
        Map<String, String> newMap = new HashMap<>();
        if (null != fieldNames) {
            for (String name : fieldNames) {
                newMap.put(name, (String) oldMap.get(name));
            }
        }
        return newMap;
    }

    /**
     * @Author Lr
     * @Date 2020/10/14 14:29
     * @Description
     */
    public static Map<String, String> parseObj2StrMap(Object args) {
        Map<String, Object> oldMap = parseObj2Map(args);
        Map<String, String> newMap = new HashMap<>();
        for (String key : oldMap.keySet()) {
            newMap.put(key, null != oldMap.get(key) ? oldMap.get(key).toString() : null);
        }
        return newMap;
    }

    /**
     * @Author Lr
     * @Description toGenericMap
     * @Date 2020/10/13 15:32
     * @Return
     */
    public static Map<String, String> toGenericMap(Map oldMap) {
        Map<String, String> newMap = new HashMap<>();
        if (null != oldMap) {
            for (Object key : oldMap.keySet()) {
                newMap.put((String) key, String.valueOf(oldMap.get(key)));
            }
        }
        return newMap;
    }

}
