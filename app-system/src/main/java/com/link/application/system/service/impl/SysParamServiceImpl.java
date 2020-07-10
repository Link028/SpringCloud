package com.link.application.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.link.application.system.dto.SysParamDTO;
import com.link.application.system.model.SysParam;
import com.link.application.system.repository.SysParamRepository;
import com.link.application.system.service.SysParamService;
import com.link.common.persist.domain.EntityStatus;
import com.link.common.persist.uuid.UUIDKeyGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class SysParamServiceImpl implements SysParamService
{

	@Autowired
	private SysParamRepository sysParamRepository;

	public Page<SysParam> find(SysParamDTO param, Pageable pageable) {

		Specification<SysParam> specification = new Specification<SysParam>()
		{
			public Predicate toPredicate(Root<SysParam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StringUtils.isNotEmpty(param.getParamName()))
				{
					predicates.add(cb.like(root.get("paramName"), "%" + param.getParamName() + "%"));
				}
				if (StringUtils.isNotEmpty(param.getParamKey()))
				{
					predicates.add(cb.like(root.get("paramKey"), "%" + param.getParamKey() + "%"));
				}
				if (StringUtils.isNotEmpty(param.getParamType()))
				{
					predicates.add(cb.equal(root.get("paramType"), param.getParamType()));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

			private static final long serialVersionUID = 1L;
		};

		Page<SysParam> data = sysParamRepository.findAll(specification, pageable);

		return data;

		// Page<SysParamDTO> page = sysParamRepository.find((root, criteriaQuery, criteriaBuilder) ->
		// QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
		// return PageUtil.toPage(page.map(sysParamMapper::toDto));
	}

	public void create(SysParamDTO param) {
		SysParam entity = new SysParam();

		BeanUtils.copyProperties(param, entity);

		entity.setParamId(UUIDKeyGenerator.getUUID());
		entity.setStatus(EntityStatus.ENABLED.getValue());

		sysParamRepository.save(entity);
	}

	@Override
	public void update(SysParamDTO param) {
		SysParam entity = new SysParam();
		BeanUtils.copyProperties(param, entity);
		sysParamRepository.save(entity);
	}

	@Override
	public void deleteById(String id) {
		sysParamRepository.deleteById(id);
	}

	@Override
	public Map<String, SysParam> findSysParam() {
		Specification<SysParam> specification = new Specification<SysParam>()
		{
			public Predicate toPredicate(Root<SysParam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.get("paramType"), "1"));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

			private static final long serialVersionUID = 1L;
		};

		List<SysParam> data = sysParamRepository.findAll(specification);

		Map<String, SysParam> sysparams = new HashMap<String, SysParam>();

		for (SysParam sysParam : data)
		{
			sysparams.put(sysParam.getParamKey(), sysParam);
		}

		return sysparams;
	}

	@Override
	public Map<String, String> findSysParmaVal() {
		Specification<SysParam> specification = new Specification<SysParam>()
		{
			public Predicate toPredicate(Root<SysParam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.get("paramType"), "1"));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

			private static final long serialVersionUID = 1L;
		};

		List<SysParam> data = sysParamRepository.findAll(specification);

		Map<String, String> sysparams = new HashMap<String, String>();

		for (SysParam sysParam : data)
		{
			sysparams.put(sysParam.getParamKey(), sysParam.getParamVal());
		}

		return sysparams;
	}
}
