package com.link.application.system.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import com.link.application.system.dto.SysOrgDTO;
import com.link.application.system.model.SysOrg;
import com.link.application.system.service.SysOrgService;
import com.link.common.MessageCode;
import com.link.common.persist.dto.TreeNode;
import com.link.common.web.RestData;
import com.link.common.web.RestPage;
import com.link.common.web.RestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/org")
public class SysOrgResource {
	
	@Autowired
    private SysOrgService sysOrgService; 

	@RequestMapping(value = "/findOrg", method = RequestMethod.GET) // Pageable pageable
    public ResponseEntity<RestData<?>> findOrg(SysOrgDTO param , RestPage page , HttpServletRequest request, HttpServletResponse response )
    {
    	try 
		{  
    		List<TreeNode> rdata = new ArrayList<TreeNode>();
    		
    		List<SysOrg> data = sysOrgService.findAll( ) ;
  
    		Iterator<SysOrg> iter = data.iterator();
    		while ( iter.hasNext()){
    			
    			SysOrg sysOrg = iter.next();
    			 
				if( sysOrg.getOrgParentId() == null || sysOrg.getOrgParentId().trim().length() == 0 )
				{
					//opens.add( sysOrg.getOrgId() );
					TreeNode root = new TreeNode(sysOrg.getOrgId() , sysOrg.getOrgFullName() );
					rdata.add(root);
					
					iter.remove( ) ;
	
					getChild( root , data ) ;
				}
    		}
    		
//      		for (SysOrg sysOrg : data)
//			{
//				
//      			TreeNode root = new TreeNode(sysOrg.getOrgId() , sysOrg.getOrgFullName() ,sysOrg.getOrgParentId() );
//      			rdata.add(root);
//			}
      		
    		
    		return new ResponseEntity<RestData<?>>( RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , rdata )  , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    }
    }
	
	protected void getChild(TreeNode root , List<SysOrg> data )
	{
		Iterator<SysOrg> iter = data.iterator();
		
		while ( iter.hasNext()){
			
			SysOrg sysOrg = iter.next();
			 
			if( sysOrg.getOrgParentId() != null && sysOrg.getOrgParentId().equals( root.getId()  ))
			{
				List<TreeNode> children = root.getChildren() ;
				if( children == null )
				{
					children = new ArrayList<TreeNode>() ;
					root.setChildren(children);
				}
				
				TreeNode child = new TreeNode(sysOrg.getOrgId() , sysOrg.getOrgFullName() , root.getId()  );
	
				children.add( child ) ;
				
				//iter.remove( ) ;
				//System.out.println( data.size() );
				
				getChild( child , data ) ;
			}
		}
		
//		for (SysOrg sysOrg : data )
//		{
//			if( sysOrg.getOrgParentId() != null && sysOrg.getOrgParentId().equals( root.getId()  ))
//			{
//				List<TreeNode> children = root.getChildren() ;
//				if( children == null )
//				{
//					children = new ArrayList<TreeNode>() ;
//					root.setChildren(children);
//				}
//				
//				TreeNode child = new TreeNode(sysOrg.getOrgId() , sysOrg.getOrgFullName() , root.getId()  );
//	
//				children.add( child ) ;
//				
//				//data.remove(sysOrg ) ;
//				
//				getChild( child , data ) ;
//			}
//		}
		
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET) // Pageable pageable
    public ResponseEntity<RestData<?>> find(SysOrgDTO param , RestPage page , HttpServletRequest request, HttpServletResponse response )
    {
    	try 
		{    
    		//System.out.println( page );
    		//System.out.println( param );
    		
            Page<SysOrg> data = sysOrgService.find( param, page.toPageRequest() ) ;
            
            //System.out.println( data );
            //System.out.println( RestUtil.toRestData(true, MessageCode.OperationSuccess.getCode() , "查询成功" , data ) );
            
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
    public ResponseEntity<RestData<?>> create(@RequestBody SysOrgDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		sysOrgService.create( param ); 
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "创建成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> update(@RequestBody SysOrgDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	try 
		{ 
    		//System.out.println( param );
    		sysOrgService.update( param ); 
    		
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody(true,MessageCode.OperationSuccess.getCode(),  "修改成功") , HttpStatus.OK);
	    } 
		catch (Exception e) 
		{
			log.error( "操作失败:"+ e.getMessage() , e  );
			return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败：" + e.getMessage() ) , HttpStatus.INTERNAL_SERVER_ERROR );
	    } 
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<RestData<?>> delete(@RequestBody SysOrgDTO param , HttpServletRequest request, HttpServletResponse response ) 
    {
    	List<SysOrg> children = sysOrgService.findByOrgParentId(param.getOrgId()) ;
    	if( children.size() > 0 )
    	{
    		return new ResponseEntity<RestData<?>>( RestUtil.createBody( false , MessageCode.OperationFailue.getCode(), "操作失败，请先删除下级机构"  ) , HttpStatus.INTERNAL_SERVER_ERROR );
    	}
    	
    	try 
		{ 
    		if( StringUtils.isNotEmpty( param.getOrgId() ) )
    		{
    			sysOrgService.deleteById( param.getOrgId() ); 
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
