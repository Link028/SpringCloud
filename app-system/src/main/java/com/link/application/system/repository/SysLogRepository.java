package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysLog;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysLogRepository extends BaseRepository<SysLog, String> { 
	

}


