package io.mersys.medis.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.repository.PatientRepository;
import io.mersys.medis.service.PatientService;
import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;
import io.mersys.medis.service.mapper.PatientMapper;
import io.mersys.medis.repository.CustomizedPatientRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{
	
	private PatientRepository repository;
	private PatientMapper mapper;

	@Override
	public List<PatientDTO> getAll() {
		return mapper.toDto(repository.findAll());
	}

	@Override
	public Optional<PatientDTO> get(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Patient 'id' is not valid value: '" + id + "'");
		}

		return repository.findById(id).map(mapper::toDto);
	}

	@Override
	public PatientDTO create(PatientDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To create Patient, 'dto' must not be null");
		}
		if (dto.getId() != null) {
			throw new RuntimeException("To create Patient, 'id' must be null");
		}

		return save(dto);
	}

	@Override
	public void delete(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Patient 'id' is not valid value: '" + id + "'");
		}

		repository.deleteById(id);
	}

	@Override
	public PatientDTO update(PatientDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To update Patient, 'dto' must not be null");
		}
		if (dto.getId() == null) {
			throw new RuntimeException("To update Patient, 'id' must not be null");
		}
		if (dto.getName() == null) {
			throw new RuntimeException("To update Patient, 'name' must not be null");
		}

		return save(dto);
	}
	
	private PatientDTO save(PatientDTO dto) {
		Patient doc = mapper.toDocument(dto);
		doc = repository.save(doc);
		return mapper.toDto(doc);
	}
	
	@Override
	public List<PatientDTO> search(PatientSearchDTO dto){		
		if (dto == null) {
			throw new IllegalArgumentException("To search Sample, 'dto' must not be null");
		}
		
		List<Patient> list = repository.findBySearchDTO(dto);		
		return mapper.toDto(list);
		
	}

}
