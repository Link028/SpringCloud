
package com.link.cloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.link.cloud.gateway.Constants;


@Configuration
public class RouterFunctionConfiguration
{
    private final HystrixFallbackHandler hystrixFallbackHandler; 
    private final ImgCodeHandler         imgCodeHandler;

	public RouterFunctionConfiguration(HystrixFallbackHandler hystrixFallbackHandler, ImgCodeHandler imgCodeHandler)
	{
		this.hystrixFallbackHandler = hystrixFallbackHandler;
		this.imgCodeHandler = imgCodeHandler;
	}
    
    @Bean
    public RouterFunction<?> routerFunction()
    {
        return RouterFunctions
                   .route(RequestPredicates.path( Constants.FALLBACK_URL ).and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET( Constants.CODE_URL).and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imgCodeHandler);
    }




}
