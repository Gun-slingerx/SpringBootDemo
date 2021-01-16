package org.example.handle;

/**
 * 映射接口
 *
 * @author admin
 */
public interface CsvHandle {

    /**
     *@description: 映射方法
     *@author:Lr
     *@createTime: 2021/1/12 10:20
     */
    <T> T csvHandleMapping(Object original, Class<T> system);

}
