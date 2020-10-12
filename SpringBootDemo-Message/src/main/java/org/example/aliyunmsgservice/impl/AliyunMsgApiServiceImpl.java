package org.example.aliyunmsgservice.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.example.aliyunmsgservice.AliyunMsgApiService;

public class AliyunMsgApiServiceImpl implements AliyunMsgApiService {


    @Override
    public CommonResponse AddSmsSign() {
        return null;
    }

    @Override
    public CommonResponse ModifySmsSign() {
        return null;
    }

    @Override
    public CommonResponse DeleteSmsSign() {
        return null;
    }

    @Override
    public CommonResponse QuerySmsSign() {
        return null;
    }

    @Override
    public CommonResponse AddSmsTemplate() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("AddSmsTemplate");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        CommonResponse response = new CommonResponse();
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public CommonResponse ModifySmsTemplate() {
        return null;
    }

    @Override
    public CommonResponse DeleteSmsTemplate() {
        return null;
    }

    @Override
    public CommonResponse QuerySmsTemplate() {
        return null;
    }

    @Override
    public CommonResponse SendSms() {
        return null;
    }

    @Override
    public CommonResponse SendBatchSms() {
        return null;
    }

    @Override
    public CommonResponse QuerySendDetails() {
        return null;
    }
}
