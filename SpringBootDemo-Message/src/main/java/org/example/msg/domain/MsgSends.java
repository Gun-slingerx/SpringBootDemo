package org.example.msg.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 短信发送记录表
 */
@ApiModel(value = "org-example-msg-domain-MsgSends")
@Data
public class MsgSends {
    /**
     * 自增主键ID
     */
    @ApiModelProperty(value = "自增主键ID")
    private Integer id;

    /**
     * 发送号码
     */
    @ApiModelProperty(value = "发送号码")
    private String phoneNumbers;

    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    private String msgContent;

    /**
     * 请求状态码，Ok代表成功，其他错误码请查看阿里云短信服务错误码列表
     */
    @ApiModelProperty(value = "请求状态码，Ok代表成功，其他错误码请查看阿里云短信服务错误码列表")
    private String msgCode;

    /**
     * 请求ID
     */
    @ApiModelProperty(value = "请求ID")
    private String requestId;

    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
     */
    @ApiModelProperty(value = "发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。")
    private String bizId;

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

    /**
     * 平台编码
     */
    @ApiModelProperty(value = "平台编码")
    private String platformCode;
}