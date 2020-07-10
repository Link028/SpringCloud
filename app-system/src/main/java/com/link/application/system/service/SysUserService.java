package com.link.application.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.application.system.dto.SysUserDTO;
import com.link.application.system.model.SysUser;

public interface SysUserService  {
	 
	public Page<SysUser> find( SysUserDTO param , Pageable pageable ) ;
	
	public void create(SysUserDTO param) ;
	
	public void update(SysUserDTO param) ;
	
	public void deleteById(String id ) ;

	public void updatePassword(SysUserDTO param);
	
}


