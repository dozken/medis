package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.doctor.Doctor;
import io.mersys.medis.service.dto.DoctorDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DoctorMapper extends DocumentMapper<DoctorDTO,Doctor>{

}
