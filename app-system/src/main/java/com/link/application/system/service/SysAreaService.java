package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysArea;
import com.link.application.system.repository.*;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysAreaService  {
	 
    @Autowired
    private SysAreaRepository sysAreaRepository ;
 
    public void create(SysArea area)
    {
    	area.setAreaId( UUIDKeyGenerator.getUUID() );  
    	area.setStatus(  EntityStatus.ENABLED.getValue() ); 
    	 
    	sysAreaRepository.save( area ) ;
    }
    
}


