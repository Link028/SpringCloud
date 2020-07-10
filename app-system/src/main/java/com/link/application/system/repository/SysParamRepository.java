package com.link.application.system.repository;

import org.springframework.stereotype.Repository;

import com.link.application.system.model.SysParam;
import com.link.common.persist.repository.BaseRepository;

@Repository
public interface SysParamRepository extends BaseRepository<SysParam, String> 
{ 
//	/**
//     * jpa支持对象查询，简称HQL，也支持原生sql查询
//     * @return
//     */
//    @Query("select p from SysParam  p ")
//    public List<SysParam> listUserEntity();

	/**
           * 复杂查询
     * @param spec 拼接的条件语句 如果有很复杂的语句比如 select * from a where a.name ='' ,
     *             a.password ='' or a.name ='' or a.name ='' or a.name in  ('','')
     * @param pageable 分页加排序 Pageable已经将这些事情做好了。
     * @return Page 形式的员工列表
     */
	//public Page<SysParam> find(Specification<SysParam> spec,Pageable pageable);
	
}


