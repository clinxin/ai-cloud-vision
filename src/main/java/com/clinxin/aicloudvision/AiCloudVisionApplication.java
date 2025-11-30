package com.clinxin.aicloudvision;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author clinxin
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.clinxin.aicloudvision.mapper")
public class AiCloudVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCloudVisionApplication.class, args);
    }

}
