package org.example.msg.service.impl;


import org.example.msg.context.SuccessEnum;
import org.example.msg.req.CreateMsgTemplateReq;
import org.example.msg.res.BaseRes;
import org.example.util.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import org.example.msg.dao.MsgTemplateMapper;
import org.example.msg.domain.MsgTemplate;
import org.example.msg.service.MsgTemplateService;

@Service
public class MsgTemplateServiceImpl implements MsgTemplateService {

    @Resource
    private MsgTemplateMapper msgTemplateMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return msgTemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MsgTemplate record) {
        return msgTemplateMapper.insert(record);
    }

    @Override
    public int insertSelective(MsgTemplate record) {
        return msgTemplateMapper.insertSelective(record);
    }

    @Override
    public MsgTemplate selectByPrimaryKey(Long id) {
        return msgTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MsgTemplate record) {
        return msgTemplateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MsgTemplate record) {
        return msgTemplateMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<MsgTemplate> list) {
        return msgTemplateMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<MsgTemplate> list) {
        return msgTemplateMapper.batchInsert(list);
    }

    @Override
    public BaseRes createMsgTemplate(CreateMsgTemplateReq createMsgTemplateReq) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1,1);
        MsgTemplate msgTemplate = new MsgTemplate();

        BeanUtils.copyProperties(createMsgTemplateReq,msgTemplate);
        msgTemplateMapper.insert(msgTemplate);

        //调用阿里云新增短信模板接口
        return new BaseRes(SuccessEnum.SUCCESS.getCode());
    }

}
