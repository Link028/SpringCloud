package com.link.application.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysAuth;
import com.link.application.system.model.SysGroup;
import com.link.application.system.model.SysOrg;
import com.link.application.system.model.SysRole;
import com.link.application.system.model.SysUser;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysAuthRepository extends BaseRepository<SysAuth, String> {

	@Query("select user from SysUser user where user.userId in ( select au.entityId from SysAuth au where au.entityType='1' and au.resId = ?1  ) ")
	List<SysUser> findUsersByResourceId(String resId ); 
 
	@Query("select role from SysRole role where role.roleId in ( select au.entityId from SysAuth au where au.entityType= '2' and au.resId = ?1  ) ")
	List<SysRole> findRolesByResourceId(String resId ); 
	 
	@Query("select group from SysGroup group where group.groupId in ( select au.entityId from SysAuth au where au.entityType='3' and au.resId = ?1  ) ")
	List<SysGroup> findGroupsByResourceId(String resId ); 

	@Query("select org from SysOrg org where org.orgId in ( select au.entityId from SysAuth au where au.entityType='4' and au.resId = ?1  ) ")
	List<SysOrg> findOrgsByResourceId(String resId ); 
	
}


