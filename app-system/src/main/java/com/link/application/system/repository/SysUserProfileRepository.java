package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysUserProfile;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysUserProfileRepository extends BaseRepository<SysUserProfile, String> { 
	

}


