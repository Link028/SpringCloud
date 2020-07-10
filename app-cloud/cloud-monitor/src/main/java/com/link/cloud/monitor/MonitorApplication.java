package com.link.cloud.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient 
@EnableAdminServer
public class MonitorApplication
{
	static Logger logger = LoggerFactory.getLogger(MonitorApplication.class);
	
    public static void main(String[] args)
    {
        SpringApplication.run(MonitorApplication.class, args);
        logger.info("「「「「「服务监控中心启动完成.」」」」」");
    }
}