package com.link.application.system.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysUser;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, String> 
{
	SysUser findByUserNum(String userNum);

	@Modifying
    @Query(value = "UPDATE SysUser user SET user.password = ?2 WHERE user.userId = ?1 ", nativeQuery = false)
    void updatePassword(String userId , String password);
}


