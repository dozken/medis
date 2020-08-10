package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.service.dto.PatientDTO;


@Mapper(componentModel = "spring", uses = {})
public interface PatientMapper extends DocumentMapper<PatientDTO,Patient> {

}