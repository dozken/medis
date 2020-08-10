package io.mersys.medis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.medis.documents.Sample;

public interface SampleRepository extends MongoRepository<Sample, String>, CustomizedSampleRepository {

	Sample findByName();

	List<Sample> findByNameOrShortNameAllIgnoreCaseLike(String name, String shortName);
}
