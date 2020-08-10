package io.mersys.medis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.medis.documents.doctor.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor,String>, CustomizedDoctorRepository{

}
