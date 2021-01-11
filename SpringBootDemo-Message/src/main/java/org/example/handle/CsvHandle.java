package org.example.handle;

import java.util.List;

/**
 * 映射接口
 * @author admin
 */
public interface CsvHandle {

    /**
     * @param original
     * @param system
     * @param list
     */
    void csvHandleMapping(Object original , Object system , List list);
}
