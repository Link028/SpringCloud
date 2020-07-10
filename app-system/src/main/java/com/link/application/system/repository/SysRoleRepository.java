package com.link.application.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysRole;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysRoleRepository extends BaseRepository<SysRole, String> { 
	@Query("select role from SysRole role where role.roleId in (select ru.roleId from SysRoleUser ru where ru.userId = ?1  ) ")
	List<SysRole> findRolesByUserId(String userId );
}


