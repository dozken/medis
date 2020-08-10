package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.Sample;
import io.mersys.medis.service.dto.SampleDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SampleMapper extends DocumentMapper<SampleDTO, Sample> {
}
