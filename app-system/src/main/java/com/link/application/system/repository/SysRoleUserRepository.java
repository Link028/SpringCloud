package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysRoleUser;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysRoleUserRepository extends BaseRepository<SysRoleUser, String> { 
	

}


