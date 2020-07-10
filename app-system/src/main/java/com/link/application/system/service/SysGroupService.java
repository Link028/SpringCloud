package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysGroup;
import com.link.application.system.repository.*;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysGroupService  {
	 
    @Autowired
    private SysGroupRepository sysGroupRepository ;
 
    public void create(SysGroup group)
    {
    	group.setGroupId( UUIDKeyGenerator.getUUID() ); 
    	group.setStatus( EntityStatus.ENABLED.getValue() ); 
    	 
    	sysGroupRepository.save( group ) ;
    }
    
    
}


