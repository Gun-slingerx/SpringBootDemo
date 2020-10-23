package org.example.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.msg.domain.MsgTemplate;

@Mapper
public interface MsgTemplateMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(MsgTemplate record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(MsgTemplate record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    MsgTemplate selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MsgTemplate record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MsgTemplate record);

    int updateBatch(List<MsgTemplate> list);

    int updateBatchSelective(List<MsgTemplate> list);

    int batchInsert(@Param("list") List<MsgTemplate> list);
}