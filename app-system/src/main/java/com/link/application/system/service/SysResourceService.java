package com.link.application.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.model.SysResource;
import com.link.application.system.repository.SysResourceRepository;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional( propagation = Propagation.REQUIRED )
public class SysResourceService  {
	 
    @Autowired
    private SysResourceRepository sysResourceRepository ;
 
    public void create(SysResource resource)
    {
    	resource.setResId( UUIDKeyGenerator.getUUID() ); 
    	resource.setStatus( EntityStatus.ENABLED.getValue() ); 
    	 
    	sysResourceRepository.save( resource ) ;
    }
    
    
}


