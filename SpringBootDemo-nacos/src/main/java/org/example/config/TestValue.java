package org.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author LR
 * @version 1.0
 * @date 2020/11/25 15:17
 * @Description
 */
@Configuration
@Data
public class TestValue {
    @Value("${spring.datasource.driverClassName}")
    private String value;
}
