package org.example.msg.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.example.msg.dao.MsgSendsMapper;
import org.example.msg.domain.MsgSends;
import org.example.msg.service.MsgSendsService;
@Service
public class MsgSendsServiceImpl implements MsgSendsService{

    @Resource
    private MsgSendsMapper msgSendsMapper;

    @Override
    public void sendMsg() {

    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return msgSendsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MsgSends record) {
        return msgSendsMapper.insert(record);
    }

    @Override
    public int insertSelective(MsgSends record) {
        return msgSendsMapper.insertSelective(record);
    }

    @Override
    public MsgSends selectByPrimaryKey(Integer id) {
        return msgSendsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MsgSends record) {
        return msgSendsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MsgSends record) {
        return msgSendsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<MsgSends> list) {
        return msgSendsMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<MsgSends> list) {
        return msgSendsMapper.batchInsert(list);
    }

}
