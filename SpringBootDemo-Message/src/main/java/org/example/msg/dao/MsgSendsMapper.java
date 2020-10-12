package org.example.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.msg.domain.MsgSends;

@Mapper
public interface MsgSendsMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(MsgSends record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(MsgSends record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    MsgSends selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MsgSends record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MsgSends record);

    int updateBatch(List<MsgSends> list);

    int batchInsert(@Param("list") List<MsgSends> list);
}