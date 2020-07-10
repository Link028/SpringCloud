package com.link.cloud.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableDiscoveryClient  
@EnableEurekaClient
@EnableEurekaServer
public class RegistryApplication
{
	static Logger logger = LoggerFactory.getLogger(RegistryApplication.class);
	
    public static void main(String[] args)
    {
        SpringApplication.run(RegistryApplication.class, args);
        logger.info("「「「「「服务注册中心启动完成.」」」」」");
    }
}