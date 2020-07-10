package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysGroupUser;
import com.link.application.system.repository.SysGroupUserRepository;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysGroupUserService  {
	 
    @Autowired
    private SysGroupUserRepository sysGroupUserRepository ;
 
    public void create(SysGroupUser gu )
    { 
    	sysGroupUserRepository.save( gu ) ;
    }
    
    
}


