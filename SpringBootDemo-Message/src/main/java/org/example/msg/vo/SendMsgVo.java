package org.example.msg.vo;

import lombok.Data;

import java.util.List;

@Data
public class SendMsgVo {

    private Long msg_template_id;

    private List<String> telList;


}
