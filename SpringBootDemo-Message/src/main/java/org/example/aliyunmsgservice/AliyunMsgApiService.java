package org.example.aliyunmsgservice;

import com.aliyuncs.CommonResponse;

public interface AliyunMsgApiService {

    /*********短信签名相关操作*********/
    CommonResponse AddSmsSign();

    CommonResponse ModifySmsSign();

    CommonResponse DeleteSmsSign();

    CommonResponse QuerySmsSign();

    /*********短信模板相关操作*********/
    CommonResponse AddSmsTemplate();

    CommonResponse ModifySmsTemplate();

    CommonResponse DeleteSmsTemplate();

    CommonResponse QuerySmsTemplate();

    /*********发送短信相关操作*********/
    CommonResponse SendSms();

    CommonResponse SendBatchSms();

    /*********查询短信列表详细相关操作*********/
    CommonResponse QuerySendDetails();
}
