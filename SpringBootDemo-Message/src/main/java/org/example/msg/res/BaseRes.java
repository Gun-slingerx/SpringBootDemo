package org.example.msg.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseRes {

    @ApiModelProperty(value = " 状态码:成功 0,失败 其他")
    private int statusCode;


}
