package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysDict;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysDictRepository extends BaseRepository<SysDict, String> { 
	

}


