package io.mersys.medis.repository;

import java.util.List;

import io.mersys.medis.documents.Sample;
import io.mersys.medis.service.dto.SampleSearchDTO;

public interface CustomizedSampleRepository {

	List<Sample> findBySearchDTO(SampleSearchDTO dto);

}
