package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysOrgUser;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysOrgUserRepository extends BaseRepository<SysOrgUser, String> { 
	

}


