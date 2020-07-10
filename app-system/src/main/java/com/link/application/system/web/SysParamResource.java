package com.link.application.system.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.link.application.system.dto.SysParamDTO;
import com.link.application.system.model.SysParam;
import com.link.application.system.service.SysParamService;
import com.link.common.MessageCode;
import com.link.common.web.RestData;
import com.link.common.web.RestPage;
import com.link.common.web.RestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/param")
public class SysParamResource {
	
	@Autowired
    private SysParamService sysParamService; 

	@RequestMapping(value = "/findparam", method = RequestMethod.GET) // Pageable pageable
    public ResponseEntity<RestData<?>> findSysParm( HttpServletRequest request, HttpServletResponse response )
    {
    	try 
		{   
    		Map<String,String> rdata = new HashMap<String,String>() ;
    		Map<String,String> data = sysParamService.findSysParmaVal( ) ;
    		for(String key : data.keySet()){
    			rdata.put(key.substring(key.lastIndexOf(".") + 1 ,key.length()), data.get(key));
    		}
    		
    		return new ResponseEntity<RestData<?>>( RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , rdata )  , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    }
    }
	
	@RequestMapping(value = "/find", method = RequestMethod.GET) // Pageable pageable
    public ResponseEntity<RestData<?>> find(SysParamDTO param , RestPage page , HttpServletRequest request, HttpServletResponse response )
    {
    	try 
		{    
    		//System.out.println( page );
    		//System.out.println( param );
    		
            Page<SysParam> data = sysParamService.find( param, page.toPageRequest() ) ;
            
            //System.out.println( data );
            //System.out.println( RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , data ) );
            
    		return new ResponseEntity<RestData<?>>( RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , data )  , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST) 
    public ResponseEntity<RestData<?>> create(@RequestBody SysParamDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		sysParamService.create( param ); 
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "创建成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> update(@RequestBody SysParamDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		//System.out.println( param );
    		sysParamService.update( param ); 
    		
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "修改成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> delete(@RequestBody SysParamDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		if( StringUtils.isNotEmpty( param.getParamId() ) )
    		{
    			sysParamService.deleteById( param.getParamId() ); 
    		}
    		
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "删除成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }
    
}
