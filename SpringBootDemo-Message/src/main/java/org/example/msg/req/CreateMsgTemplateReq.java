package org.example.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateMsgTemplateReq {

    @ApiModelProperty(value="模板名称，长度为1~30个字符。")
    private String templateName;

    /**
     * 短信类型：其中 0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息。
     */
    @ApiModelProperty(value="短信类型：其中 0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息。")
    private Byte templateCode;

    /**
     * 模板内容，长度为1~500个字符。
     */
    @ApiModelProperty(value="模板内容，长度为1~500个字符。")
    private String templateContent;

    /**
     * 短信模板申请说明，请在申请说明中描述您的业务使用场景，长度为1~100个字符。示例：您正在申请手机注册，验证码为：${code}，5分钟内有效！
     */
    @ApiModelProperty(value="短信模板申请说明，请在申请说明中描述您的业务使用场景，长度为1~100个字符。示例：您正在申请手机注册，验证码为：${code}，5分钟内有效！")
    private String remark;

    /**
     * 平台编码
     */
    @ApiModelProperty(value="平台编码")
    private String platformCode;
}
