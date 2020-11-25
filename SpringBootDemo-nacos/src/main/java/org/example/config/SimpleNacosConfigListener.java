package org.example.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SystemUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author LR
 * @version 1.0
 * @date 2020/11/25 20:32
 * @Description
 */
@Configuration
@Slf4j
public class SimpleNacosConfigListener {

    @NacosInjected
    private ConfigService configService;

    @PostConstruct
    public void init() throws Exception {
        // Build Properties Content
        StringBuilder builder = new StringBuilder();
        builder.append("user.id = 1");
        builder.append(SystemUtils.LINE_SEPARATOR);
        builder.append("user.name = mercyblitz");
        builder.append(SystemUtils.LINE_SEPARATOR);
        builder.append("user.github = https://github.com/mercyblitz");
        configService.publishConfig("common.properties", "DEFAULT_GROUP",
                builder.toString());
    }

    @NacosConfigListener(dataId = "common.properties")
    public void onReceived(String value) {
        log.info("onReceived(String) : {}", value);
    }

    @NacosConfigListener(dataId = "common.properties")
    public void onReceived(Properties value) {
        log.info("onReceived(Properties) : {}", value);
    }
}
