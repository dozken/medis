package io.mersys.medis.repository;
import java.util.List;

import io.mersys.medis.documents.doctor.*;
import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;;
public interface CustomizedDoctorRepository {
	List<Doctor> findBySearchDTO(DoctorSearchDTO dto);
}
