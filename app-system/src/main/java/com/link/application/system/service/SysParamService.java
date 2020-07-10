package com.link.application.system.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.application.system.dto.SysParamDTO;
import com.link.application.system.model.SysParam;

public interface SysParamService
{
	public Page<SysParam> find( SysParamDTO param , Pageable pageable ) ;
	
	public Map<String,SysParam> findSysParam( ) ;
	
	public Map<String,String> findSysParmaVal( ) ;
	
	public void create(SysParamDTO param) ;
	
	public void update(SysParamDTO param) ;
	
	public void deleteById(String id) ;
}
