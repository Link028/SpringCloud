package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysResource;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysResourceRepository extends BaseRepository<SysResource, String> { 
	

}


