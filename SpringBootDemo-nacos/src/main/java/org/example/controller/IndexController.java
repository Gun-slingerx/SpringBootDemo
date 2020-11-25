package org.example.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.example.config.SimpleNacosConfigListener;
import org.example.config.TestValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LR
 * @version 1.0
 * @date 2020/11/25 15:06
 * @Description
 */
@RequestMapping
@RestController
@RefreshScope
public class IndexController {
    @Autowired
    private TestValue testValue;


    @GetMapping("index")
    public String index() {
        return testValue.getValue();
    }

    @GetMapping("testListen")
    public String testListen() throws NacosException {
        return "";
    }
}
