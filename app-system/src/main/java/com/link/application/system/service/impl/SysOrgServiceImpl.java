package com.link.application.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.dto.SysOrgDTO;
import com.link.application.system.model.SysOrg;
import com.link.application.system.repository.SysOrgRepository;
import com.link.application.system.service.SysOrgService;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false , rollbackFor = Exception.class)
public class SysOrgServiceImpl  implements  SysOrgService{
	 
    @Autowired
    private SysOrgRepository sysOrgRepository ;
   
    public List<SysOrg> findAll(  )
    {
    	List<SysOrg> data = sysOrgRepository.findAll();
    	
    	return data ;
    }
    
    public Page<SysOrg> find( SysOrgDTO org , Pageable pageable ) {
     
    	    Specification<SysOrg> specification = new Specification<SysOrg>() {
			public Predicate toPredicate(Root<SysOrg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if( StringUtils.isNotEmpty( org.getOrgNum() ) ){
					predicates.add(cb.equal( root.get("orgNum") ,  org.getOrgNum() ));
				}
				if( StringUtils.isNotEmpty( org.getOrgFullName() )) {
					predicates.add(cb.like(root.get("orgFullName"), "%" + org.getOrgFullName() + "%"));
				}
				if( StringUtils.isNotEmpty( org.getOrgType() )) {
					predicates.add(cb.equal(root.get("orgType"),  org.getOrgType() ));
				}
				
				if( StringUtils.isNotEmpty( org.getOrgParentId() ) ){
					predicates.add(cb.equal( root.get("orgParentId") ,  org.getOrgParentId() ));
				}
				
//				if( StringUtils.isNotEmpty( org.getOrgParentId() )) {
//	                    javax.persistence.criteria.Join<SysOrg,SysOrg> parentOrg = root.join("parent",javax.persistence.criteria.JoinType.LEFT);
//	                    Predicate orgPredicate = cb.equal(parentOrg.get("orgId"), org.getOrgParentId() );
//	                    predicates.add( orgPredicate );
//	            }
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			private static final long serialVersionUID = 1L;
		};
		
		 Page<SysOrg>  data = sysOrgRepository.findAll(specification,pageable) ;
		 
         for (SysOrg sysOrg : data.getContent() )
		 {
			if( sysOrg.getOrgParentId() != null )
			{
				Optional<SysOrg> pOrg = sysOrgRepository.findById( sysOrg.getOrgParentId()) ;
				if( pOrg.isPresent() )
				{
					sysOrg.setOrgParentName(  pOrg.get().getOrgFullName() );
				}
			}
		 }
		 
         return data ;
    }

    public List<SysOrg> findByOrgParentId(String orgParentId ) {
    	return sysOrgRepository.findByOrgParentId(orgParentId);
    }
    
	public void create(SysOrgDTO org)
    {
		SysOrg entity = new SysOrg();
		
		BeanUtils.copyProperties(org, entity);

		entity.setOrgParentId( org.getOrgParentId() );
		
		entity.setOrgId( UUIDKeyGenerator.getUUID() );
		entity.setStatus( EntityStatus.ENABLED.getValue() ); 
    	 
		sysOrgRepository.save( entity ) ;
    }
    
	@Override
	public void update(SysOrgDTO org) {
		SysOrg entity = new SysOrg();
		BeanUtils.copyProperties(org, entity);
		sysOrgRepository.save( entity ) ;
	}

	@Override
	public void deleteById(String id) {
		sysOrgRepository.deleteById(id);
	}

}


