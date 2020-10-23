package org.example.msg.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 测试表
    */
@ApiModel(value="org-example-msg-domain-Test")
@Data
public class Test {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * 内容
    */
    @ApiModelProperty(value="内容")
    private String content;
}