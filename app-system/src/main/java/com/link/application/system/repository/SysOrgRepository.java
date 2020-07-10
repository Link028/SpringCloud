package com.link.application.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysOrg;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysOrgRepository extends BaseRepository<SysOrg, String> { 
	
	@Query("select org from SysOrg org where org.orgId in (select ou.orgId from SysOrgUser ou where ou.userId = ?1  ) ")
	List<SysOrg> findOrgsByUserId(String userId );

	@Query("select org from SysOrg org where org.orgParentId = ?1  ")
	List<SysOrg> findByOrgParentId(String orgParentId );
	
//	@Query("select porg.orgName, org from SysOrg org where ")
//	List<SysOrg> findOrg(String userId );
	
//	
//	 @Query(value = "SELECT new com.read.first.pojo.Component (t.id,t.co_name,e.experiment_code,e.experiment_name) \n" +
//	            "FROM\n" +
//	            "ComponentEntity t, ComponentFlowEntity tf, ExperimentApplyEntity e " +
//	            "WHERE\n" +
//	            "t.id = tf.component_id\n" +
//	            "AND tf.experiment_apply_id = e.id\n" +
//	            "AND e.experiment_name LIKE ?1 ")
//	    Page<Component> findAll(String experiment_name, Pageable pageable);
	
}


