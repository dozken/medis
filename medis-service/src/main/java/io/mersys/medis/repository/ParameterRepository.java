package io.mersys.medis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.medis.documents.Parameter;

public interface ParameterRepository extends MongoRepository<Parameter, String>, CustomizedParameterRepository {
}
