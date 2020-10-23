package org.example.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.msg.domain.Test;

@Mapper
public interface TestMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Test record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Test record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Test selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Test record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Test record);

    int updateBatch(List<Test> list);

    int updateBatchSelective(List<Test> list);

    int batchInsert(@Param("list") List<Test> list);
}