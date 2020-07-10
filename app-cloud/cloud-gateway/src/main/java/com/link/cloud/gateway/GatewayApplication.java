package com.link.cloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class GatewayApplication
{
	static Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
	
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class, args);
        logger.info("「「「「「服务网关中心启动完成.」」」」」");
    }
    
}