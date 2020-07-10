package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysDictData;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysDictDataRepository extends BaseRepository<SysDictData, String> { 
	

}


