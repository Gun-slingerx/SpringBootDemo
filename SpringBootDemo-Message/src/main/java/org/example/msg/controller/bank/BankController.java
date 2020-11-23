package org.example.msg.controller.bank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LR
 * @version 1.0
 * @date 2020/10/26 17:12
 * @Description
 */
@RestController
@RequestMapping("/notice")
@Api(value = "回调接口")
public class BankController {


    @ApiOperation(value = "工商银行回调接口")
    @RequestMapping(value = {"/icbc"}, method = RequestMethod.POST)
    public String icbcNotice(@RequestBody String req) {
        System.out.println("调用成功");
        System.out.println("入参内容为 /n");
        System.out.println(req);

        String res = "\n" +
                " \"msg\":\"success\",\n" +
                "    \"code\":0,\n" +
                "    \"data\":{\n" +
                "        \"msg\":\"通知成功\",\n" +
                "        \"code\":1\n" +
                "    }";

        return res;
    }

}
