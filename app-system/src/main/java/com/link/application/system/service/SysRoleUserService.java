package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysRoleUser;
import com.link.application.system.repository.SysRoleUserRepository;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysRoleUserService  {
	 
    @Autowired
    private SysRoleUserRepository sysUserRoleRepository ;
 
    public void create(SysRoleUser ru)
    { 
    	sysUserRoleRepository.save( ru ) ;
    }
    
    
}


