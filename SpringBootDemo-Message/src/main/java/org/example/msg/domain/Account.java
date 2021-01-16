package org.example.msg.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *@description:
 *@author:Lr
 *@createTime:2021/1/14 11:40
 *@see:org.example.msg.domain
 */

/**
 * 账号表
 */
@ApiModel(value = "org-example-msg-domain-Account")
@Data
public class Account {
    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String account;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String password;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile1;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
}