package org.example.msg.service;

import java.util.List;
import org.example.msg.domain.MsgSends;
public interface MsgSendsService{

    /**
     * 发送短信
     */
    void sendMsg();

    int deleteByPrimaryKey(Integer id);

    int insert(MsgSends record);

    int insertSelective(MsgSends record);

    MsgSends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgSends record);

    int updateByPrimaryKey(MsgSends record);

    int updateBatch(List<MsgSends> list);

    int batchInsert(List<MsgSends> list);

}
