package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysRole;
import com.link.application.system.repository.SysRoleRepository;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysRoleService  {
	 
    @Autowired
    private SysRoleRepository sysRoleRepository ;
 
    public void create(SysRole role)
    {
    	role.setRoleId( UUIDKeyGenerator.getUUID() );
    	role.setStatus( EntityStatus.ENABLED.getValue() ); 
    	 
    	sysRoleRepository.save( role ) ;
    }
    
    
}


