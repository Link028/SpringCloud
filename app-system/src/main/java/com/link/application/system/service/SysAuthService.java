package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysAuth;
import com.link.application.system.repository.*;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysAuthService  {
	 
    @Autowired
    private SysAuthRepository sysAuthRepository ;
 
    public void create(SysAuth auth)
    {
    	auth.setAuthId( UUIDKeyGenerator.getUUID() ); 
    	auth.setStatus(  EntityStatus.ENABLED.getValue() ); 
    	 
    	sysAuthRepository.save( auth ) ;
    }
    
    
}


