package org.example.msg.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.msg.domain.Account;

import java.util.List;

/**
 * @description:
 * @author:Lr
 * @createTime:2021/1/14 11:40
 * @see:org.example.msg.dao
 */
@Mapper
public interface AccountMapper {
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
    int insert(Account record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Account record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Account selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int updateBatchSelective(List<Account> list);

    int batchInsert(@Param("list") List<Account> list);
}