package com.link.application.system.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.application.system.dto.SysOrgDTO;
import com.link.application.system.model.SysOrg;

public interface SysOrgService  {
	
	public List<SysOrg> findAll(  ) ;
	
	public Page<SysOrg> find( SysOrgDTO param , Pageable pageable ) ;
	
	public List<SysOrg> findByOrgParentId(String orgParentId ) ;
	
	public void create(SysOrgDTO param) ;
	
	public void update(SysOrgDTO param) ;
	
	public void deleteById(String id) ;
    
}


