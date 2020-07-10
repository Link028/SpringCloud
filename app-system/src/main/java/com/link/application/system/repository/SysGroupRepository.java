package com.link.application.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysGroup;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysGroupRepository extends BaseRepository<SysGroup, String> {
	@Query("select group from SysGroup group where group.groupId in (select gu.groupId from SysGroupUser gu where gu.userId = ?1  ) ")
	List<SysGroup> findGroupsByUserId(String userId );

}


