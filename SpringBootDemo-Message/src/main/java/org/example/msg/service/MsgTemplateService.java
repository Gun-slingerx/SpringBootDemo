package org.example.msg.service;

import java.util.List;
import org.example.msg.domain.MsgTemplate;
import org.example.msg.req.CreateMsgTemplateReq;
import org.example.msg.res.BaseRes;
import org.springframework.web.bind.annotation.RequestBody;

public interface MsgTemplateService{

    int deleteByPrimaryKey(Long id);

    int insert(MsgTemplate record);

    int insertSelective(MsgTemplate record);

    MsgTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgTemplate record);

    int updateByPrimaryKey(MsgTemplate record);

    int updateBatch(List<MsgTemplate> list);

    int batchInsert(List<MsgTemplate> list);


    BaseRes createMsgTemplate(@RequestBody CreateMsgTemplateReq createMsgTemplateReq);


}
