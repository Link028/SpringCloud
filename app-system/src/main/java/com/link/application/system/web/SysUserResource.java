package com.link.application.system.web;

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

import com.link.application.system.dto.SysUserDTO;
import com.link.application.system.model.SysUser;
import com.link.application.system.service.SysUserService;
import com.link.common.MessageCode;
import com.link.common.UserConstants;
import com.link.common.web.RestData;
import com.link.common.web.RestPage;
import com.link.common.web.RestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserResource {
	
	@Autowired
    private SysUserService sysUserService; 

	@RequestMapping(value = "/find", method = RequestMethod.GET) // Pageable pageable
    public ResponseEntity<RestData<?>> find(SysUserDTO param , RestPage page , HttpServletRequest request, HttpServletResponse response )
    {
    	try 
		{   
            Page<SysUser> data = sysUserService.find( param, page.toPageRequest() ) ;
            RestData<Map<String,Object>> rd = RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , data ) ;
    		return new ResponseEntity<RestData<?>>( rd  , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST) 
    public ResponseEntity<RestData<?>> create(@RequestBody SysUserDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		sysUserService.create( param ); 
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "创建成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> update(@RequestBody SysUserDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{  
    		sysUserService.update( param ); 
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "修改成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> delete(@RequestBody SysUserDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		if( UserConstants.ADMIN_USER.equals( param.getUserNum() ))
    		{
    			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：管理员账号不能删除。"  ) , HttpStatus.INTERNAL_SERVER_ERROR );
    		}
    		
    		if( StringUtils.isNotEmpty( param.getUserId() ) )
    		{
    			//sysUserService.delete( param ); 
    			sysUserService.deleteById( param.getUserId() );
    		}
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "删除成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }
    
    @RequestMapping(value = "/resert", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> resert(@RequestBody SysUserDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		if( StringUtils.isNotEmpty( param.getUserId() ) )
    		{
    			sysUserService.updatePassword( param ); 
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
