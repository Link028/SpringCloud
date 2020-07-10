package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysOrgUser;
import com.link.application.system.repository.SysOrgUserRepository;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysOrgUserService  {
	 
    @Autowired
    private SysOrgUserRepository sysOrgUserRepository ;
 
    public void create(SysOrgUser ou )
    { 
    	sysOrgUserRepository.save( ou ) ;
    }
    
    
}


