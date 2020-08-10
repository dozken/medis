package io.mersys.medis.repository.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.medis.documents.Parameter;
import io.mersys.medis.documents.type.ParameterType;
import io.mersys.medis.repository.CustomizedParameterRepository;
import io.mersys.medis.service.dto.ParameterSearchDTO;

public class CustomizedParameterRepositoryImpl implements CustomizedParameterRepository {

	@Autowired
	MongoTemplate template;

	@Override
	public List<Parameter> findBySearchDto(ParameterSearchDTO dto) {
		final Query q = new Query();
		if (dto.getType() != null) {
			q.addCriteria(Criteria.where("type").is(dto.getType()));
		}
		if (dto.getValue() != null && !dto.getValue().trim().isEmpty()) {
			q.addCriteria(Criteria.where("value").regex(Pattern.compile(Pattern.quote(dto.getValue().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		q.with(new Sort(Sort.Direction.DESC, "_id"));
		return template.find(q, Parameter.class);
	}
}