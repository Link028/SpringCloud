package com.link.application.system;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
//@EntityScan(basePackageClasses = { SystemApplication.class})
//@EnableJpaRepositories("com.git.hui.boot.jpacase")
@ComponentScan(basePackages = {"com.link.application.system","com.link.common.configration.druid","com.link.common.configration.spring"})
public class SystemApplication
{
	static Logger logger = LoggerFactory.getLogger(SystemApplication.class);
	
    static ConfigurableApplicationContext configurableApplicationContext ;
	
    public static void main(String[] args)
    {
    	configurableApplicationContext = SpringApplication.run(SystemApplication.class, args);
    	
    	SystemContext.loadContext();
    	
        logger.info("「「「「「系统服务启动完成.」」」」」");
        
        //Runtime.getRuntime().addShutdownHook(new Thread() {  public void run() { shutdownApp() ; } });
    }
    
	/**
	 * 测试返回的请求方法
	 *
	 * @return
	 */
    @RequestMapping(value = "/shutdown" , method = RequestMethod.GET)
	public ResponseEntity<String> shutdown() 
	{
    	logger.info("shutdown...........................");
    	
		try
		{
			shutdownApp();  
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>("ERROR",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.BAD_REQUEST);
	}
    
    public static void shutdownApp() 
    {
    	logger.info("开始关闭 应用 ..............."); 
    	
    	int n = 10 ;
		while( n-- > 1  )
		{
			try
			{
				TimeUnit.MILLISECONDS.sleep(1000);
				logger.info("Waiting for application close............" + n );
			}
			catch (InterruptedException e)
			{ 
				logger.warn("Waiting for application close error:" + e.getMessage() );
			}
		}
    	
    	configurableApplicationContext.close(); 
    	
    	logger.info("「「「「「系统服务关闭完成.」」」」」"); 
    }
     
}