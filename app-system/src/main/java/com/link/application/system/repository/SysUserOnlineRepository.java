package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysUserOnline;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysUserOnlineRepository extends BaseRepository<SysUserOnline, String> { 
	

}


