package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysGroupUser;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysGroupUserRepository extends BaseRepository<SysGroupUser, String> {
	
}


