package com.link.application.system.mapper;


import org.mapstruct.Mapper;

import com.link.application.system.dto.SysParamDTO;
import com.link.application.system.model.SysParam;
import com.link.common.persist.mapper.BaseMapper;


@Mapper(componentModel = "spring")
public interface SysParamMapper extends BaseMapper<SysParamDTO, SysParam> {

}
