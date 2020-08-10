package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.Parameter;
import io.mersys.medis.service.dto.ParameterDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SysParameterMapper extends DocumentMapper<ParameterDTO,Parameter> {

}
