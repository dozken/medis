package io.mersys.medis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.medis.documents.patient.Patient;

public interface PatientRepository extends MongoRepository<Patient, String>, CustomizedPatientRepository {

}
