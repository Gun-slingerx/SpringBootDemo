package org.example.msg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.msg.domain.Test;
import org.example.msg.req.CreateMsgTemplateReq;
import org.example.msg.req.TestReq;
import org.example.msg.res.BaseRes;
import org.example.msg.service.MsgTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Api(value = "短信模板")
public class MsgTemplateController {

    @Autowired
    private MsgTemplateService msgTemplateService;


    @ApiOperation(value = "创建短信模板")
    @RequestMapping(value = {"/createMsgTemplate"}, method = RequestMethod.POST)
    public BaseRes createMsgTemplate(@RequestBody CreateMsgTemplateReq createMsgTemplateReq){
        return msgTemplateService.createMsgTemplate(createMsgTemplateReq);
    }

    public BaseRes createTest(@RequestBody TestReq testReq){
        Test test = new Test();
        msgTemplateService.insert(test);
        return new BaseRes(0);
    }
}

