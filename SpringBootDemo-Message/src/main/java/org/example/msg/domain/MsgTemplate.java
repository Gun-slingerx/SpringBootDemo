package org.example.msg.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 短信模板表
 */
@ApiModel(value = "org-example-msg-domain-MsgTemplate")
@Data
public class MsgTemplate {
    /**
     * 自增主键ID
     */
    @ApiModelProperty(value = "自增主键ID")
    private Long id;

    /**
     * 模板名称，长度为1~30个字符。
     */
    @ApiModelProperty(value = "模板名称，长度为1~30个字符。")
    private String templateName;

    /**
     * 短信模板code
     */
    @ApiModelProperty(value = "短信模板code")
    private String templateCode;

    /**
     * 模板内容，长度为1~500个字符。
     */
    @ApiModelProperty(value = "模板内容，长度为1~500个字符。")
    private String templateContent;

    /**
     * 模板审核状态。其中：0：审核中。1：审核通过。2：审核失败，请在返回参数Reason中查看审核失败原因。
     */
    @ApiModelProperty(value = "模板审核状态。其中：0：审核中。1：审核通过。2：审核失败，请在返回参数Reason中查看审核失败原因。")
    private Byte templateStatus;

    /**
     * 短信类型,其中：0：验证码。1：短信通知。2：推广短信。3：国际/港澳台消息。
     */
    @ApiModelProperty(value = "短信类型,其中：0：验证码。1：短信通知。2：推广短信。3：国际/港澳台消息。")
    private Byte templateType;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = "审核备注")
    private String reason;

    /**
     * 短信模板申请说明，请在申请说明中描述您的业务使用场景，长度为1~100个字符。示例：您正在申请手机注册，验证码为：${code}，5分钟内有效！
     */
    @ApiModelProperty(value = "短信模板申请说明，请在申请说明中描述您的业务使用场景，长度为1~100个字符。示例：您正在申请手机注册，验证码为：${code}，5分钟内有效！")
    private String remark;

    /**
     * 1，阿里云 2，网易云信(暂时未开通) 3，其他
     */
    @ApiModelProperty(value = "1，阿里云 2，网易云信(暂时未开通) 3，其他")
    private Byte smsserviceplatCode;

    /**
     * 平台编码
     */
    @ApiModelProperty(value = "平台编码")
    private Long platformCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String created;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String modified;

    /**
     * 有效标志 1有效 0无效
     */
    @ApiModelProperty(value = "有效标志 1有效 0无效")
    private Byte activeFlag;
}