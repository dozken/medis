package io.mersys.medis.repository;

import java.util.List;

import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.service.dto.PatientSearchDTO;

public interface CustomizedPatientRepository {

	List<Patient> findBySearchDTO(PatientSearchDTO dto);
}
