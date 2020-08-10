package io.mersys.medis.repository.impl;

import io.mersys.medis.documents.doctor.Doctor;
import io.mersys.medis.repository.CustomizedDoctorRepository;
import io.mersys.medis.service.dto.DoctorSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

public class CustomizedDoctorRepositoryImpl implements CustomizedDoctorRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Doctor> findBySearchDTO(DoctorSearchDTO dto) {
        final Query query = new Query();
        if (dto.getFullInfo() != null && !dto.getFullInfo().trim().isEmpty()) {
            Criteria checkForName = Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
            Criteria checkForSurname = Criteria.where("surname").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
            Criteria checkForPatronymic = Criteria.where("patronymic").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
            Criteria checkForPin = Criteria.where("pin").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
            Criteria checkForPhone = Criteria.where("address.phoneNumber").regex(Pattern.compile(Pattern.quote(dto.getFullInfo().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
            Criteria checkFullInfo = new Criteria().orOperator(checkForName, checkForSurname, checkForPatronymic, checkForPin, checkForPhone);
            query.addCriteria(checkFullInfo);

        }
        if (dto.getDepType() != null) {
            query.addCriteria(Criteria.where("role.depType").is(dto.getDepType()));
        }
        if (dto.getRoleType() != null) {
            query.addCriteria(Criteria.where("role.roleType").is(dto.getRoleType()));
        }
        return template.find(query, Doctor.class);
    }
}