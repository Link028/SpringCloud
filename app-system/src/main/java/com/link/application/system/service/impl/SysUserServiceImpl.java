package com.link.application.system.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.link.application.system.SystemContext;
import com.link.application.system.dto.SysUserDTO;
import com.link.application.system.model.SysOrg;
import com.link.application.system.model.SysUser;
import com.link.application.system.model.SysUserProfile;
import com.link.application.system.repository.SysUserProfileRepository;
import com.link.application.system.repository.SysUserRepository;
import com.link.application.system.service.SysUserService;
import com.link.common.persist.PersistException;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;
import com.link.util.RandomUtils;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false , rollbackFor = Exception.class)
public class SysUserServiceImpl  implements  SysUserService{
	  
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
    @Autowired
    private SysUserRepository sysUserRepository ;
   
    @Autowired
    private SysUserProfileRepository sysUserProfileRepository ;
    
    public Page<SysUser> find( SysUserDTO user , Pageable pageable ) {
     
    	    @SuppressWarnings("serial")
			Specification<SysUser> specification = new Specification<SysUser>() {
    	    	
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				//if( StringUtils.isNotEmpty( user.getOrgId() ) ){
				//	predicates.add(cb.equal( root.get("orgId") ,  user.getOrgId() ));
				//}
				
				if( StringUtils.isNotEmpty( user.getUserNum() ) ){
					predicates.add(cb.equal( root.get("userNum") ,  user.getUserNum() ));
				}
				
				if( StringUtils.isNotEmpty( user.getUserName() )) {
					predicates.add(cb.like(root.get("userName"), "%" + user.getUserName() + "%"));
				}
				
				if( StringUtils.isNotEmpty( user.getOrgId() )) {
					
	                    javax.persistence.criteria.Join<SysUser , SysOrg  > org = root.join( "org" , javax.persistence.criteria.JoinType.LEFT );
	                    Predicate orgPredicate = cb.equal(org.get("orgId"), user.getOrgId() );
	                    predicates.add( orgPredicate );
	            }
				
				predicates.add(cb.equal( root.get("status"), EntityStatus.ENABLED.getValue() ) );
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
		
		 Page<SysUser>  data = sysUserRepository.findAll(specification,pageable) ;
		
        return data ;
    }

	public void create(SysUserDTO user)
    {
		SysUser entity = new SysUser();
		
		BeanUtils.copyProperties( user , entity );
		 
//		if( StringUtils.isNotEmpty( org.getOrgParentId() ) ){
//			//entity.setOrgParentId("0");
//			SysUser parent= new SysUser();
//			parent.setOrgId( org.getOrgParentId() );
//			entity.setParent(parent);
//	    }
		
		entity.setPassword( encoder.encode( SystemContext.getParam("config.user.defpasswd") ) ); 
    	entity.setSalt( RandomUtils.getRandomPassword(12) ); 
		entity.setLastPasswordResetDate(System.currentTimeMillis() ) ;
		
		entity.setUserId( UUIDKeyGenerator.getUUID() );
		entity.setStatus( EntityStatus.ENABLED.getValue() ); 
    	 
//		SysOrg org= new SysOrg();
//		org.setOrgId( user.getOrgId() );
//		entity.setOrg(org);
		
		sysUserRepository.save( entity ) ;
		
		//config.view.minidraw	false	是	侧边栏是否最小化	
		//config.view.permanent	false	是	侧边栏是否固定	
		//config.view.rtl	false	是	是否使用rtl视图	
		//config.view.tag	false	是	是否使用Tag视图
		//config.view.darkthem	false	是	是否使用暗色主题
		//config.view.color.leftmenu	#010356	是	leftmenu color	
		//config.view.color.primary	#010356	是	primary color	
		
		//config.view.color.success	#4CAF50	是	success color	
		//config.view.color.secondary	#424242	是	secondary color	
		//config.view.color.warning	#FFC107	是	warning color	
		//config.view.color.error	#FF5252	是	error color	
		//config.view.color.accent	#82B1FF	是	accent color	
		//config.view.color.info	#2196F3	是	info color	
		//config.view.color.darkprimary	#101010	是	darkprimary color	
		
		SysUserProfile userProfile = new  SysUserProfile();
		userProfile.setUserId( entity.getUserId() );
		
		userProfile.setMinidraw(  SystemContext.getParam("config.view.minidraw"));
		userProfile.setPermanent( SystemContext.getParam("config.view.permanent") );
		userProfile.setRtl( SystemContext.getParam("config.view.rtl") );
		userProfile.setTag( SystemContext.getParam("config.view.tag") );
		userProfile.setDarkthem( SystemContext.getParam("config.view.darkthem") );
		
		userProfile.setLeftmenuColor( SystemContext.getParam("config.view.color.leftmenu") );
		userProfile.setPrimaryColor( SystemContext.getParam("config.view.color.primary") );
		
		//todo
		sysUserProfileRepository.save( userProfile ) ;
    }
    
	@Override
	public void update(SysUserDTO user) {
		
		//SysUser entity =  sysUserRepository.findById( user.getUserId() ) ;
		
		sysUserRepository.findById( user.getUserId() )
        .map( entity -> {
        	
        	//entity.setTitle(questionRequest.getTitle());
        	//entity.setDescription(questionRequest.getDescription());
        	
        	BeanUtils.copyProperties(user, entity);
        	
//    		SysOrg org= new SysOrg();
//    		org.setOrgId( user.getOrgId() );
//    		entity.setOrg(org);
    		  
            return sysUserRepository.save( entity ) ;
            
        }).orElseThrow(() -> new PersistException("User not found with id " +  user.getUserId()  ));
	}  

	@Override
	public void deleteById(String id ) {
				sysUserRepository.findById( id )
		        .map( entity -> {
		        	sysUserRepository.delete(entity);
		        	return ResponseEntity.ok().build();
		        }).orElseThrow(() -> new PersistException("User not found with id " +  id  ));
		
	}

	public static void main(String[] args) {
//		System.out.println( RandomUtils.getRandomPassword(12) );
//		System.out.println( RandomUtils.getRandomPassword(12) );
//		System.out.println( RandomUtils.getRandomPassword(12) );
//		System.out.println( RandomUtils.getRandomPassword(12) );
//		System.out.println( RandomUtils.getRandomPassword(12) );
//		System.out.println( RandomUtils.getRandomPassword(12) );
		
		SysUserDTO user = new SysUserDTO();
		
		user.setUserId("11111");
		
		SysUser entity = new SysUser();
		entity.setUserName("aaaaaaaaaaaaaaa");
		
		BeanUtils.copyProperties( user, entity );
		
		System.out.println( entity  );
		
	}

	@Override
	public void updatePassword(SysUserDTO param) {
		String userId = param.getUserId() ;
		String password = encoder.encode( SystemContext.getParam("config.user.defpasswd") ) ;
		sysUserRepository.updatePassword( userId , password );
		
	}
}


