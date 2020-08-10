package io.mersys.medis.repository.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.repository.CustomizedPatientRepository;
import io.mersys.medis.service.dto.PatientSearchDTO;

public class CustomizedPatientRepositoryImpl implements CustomizedPatientRepository {

	@Autowired
	private MongoTemplate template;
	
	@Override
	public List<Patient> findBySearchDTO(PatientSearchDTO dto) {
		final Query query = new Query();
		List<Patient> list;
		//Query for Search
		if (dto.getFullInfo() != null && !dto.getFullInfo().trim().isEmpty()) {
			Criteria checkForName = Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
			Criteria checkForSurname = Criteria.where("surname").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
			Criteria checkForPatronymic = Criteria.where("patronymic").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)); 
			Criteria checkForPin = Criteria.where("pin").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
			//query.addCriteria(checkForName.orOperator(checkForSurname,checkForPatronymic,checkForPin));
			Criteria checkFullInfo = new Criteria().orOperator(checkForName,checkForSurname,checkForPatronymic,checkForPin);
			query.addCriteria(checkFullInfo);			
		}		
		//Query for Filter
		else {
			if (dto.getGender() != null) {
				if(dto.getGender().getValue() == "MALE") {
					query.addCriteria(Criteria.where("gender").is(dto.getGender().MALE));
				}
				else
					if(dto.getGender().getValue() == "FEMALE") {
						query.addCriteria(Criteria.where("gender").is(dto.getGender().FEMALE));
					}
			}
			query.with(new Sort(Sort.Direction.DESC, "_id"));			
			list = template.find(query, Patient.class);
			
		// TODO check for age
			System.out.println(dto.getAgeFrom()+" "+dto.getAgeTo());
			 if (dto.getAgeFrom() >= 0 && dto.getAgeTo() >= dto.getAgeFrom()) {
				list = list.stream().filter(x -> isInRange(x.getDateOfBirth(), dto.getAgeFrom(), dto.getAgeTo()))
						.collect(Collectors.toList());
			}
			return list;
		}
		return template.find(query, Patient.class);
	}

	private boolean isInRange(LocalDate birthDate, int startAge, int endAge) {
		int patientAge = Period.between(birthDate, LocalDate.now()).getYears();
		return patientAge >= startAge && patientAge <= endAge;
	}

}
