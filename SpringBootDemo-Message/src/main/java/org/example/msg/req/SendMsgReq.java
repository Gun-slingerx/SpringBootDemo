package org.example.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;


@Data
public class SendMsgReq {

    @ApiModelProperty(value = "模板ID")
    private Long msg_template_id;

    @ApiModelProperty(value = "手机号码列表")
    private List<String> phoneList;

    @ApiModelProperty(value = "模板内容")
    private Map<String,String> templateParamMap;

}
