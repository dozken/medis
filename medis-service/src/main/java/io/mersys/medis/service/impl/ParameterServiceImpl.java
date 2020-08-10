package io.mersys.medis.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.medis.documents.Parameter;
import io.mersys.medis.repository.ParameterRepository;
import io.mersys.medis.service.ParameterService;
import io.mersys.medis.service.dto.ParameterDTO;
import io.mersys.medis.service.dto.ParameterSearchDTO;
import io.mersys.medis.service.mapper.SysParameterMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ParameterServiceImpl implements ParameterService {

	private ParameterRepository repository;
	private SysParameterMapper mapper;

	@Override
	public List<ParameterDTO> getAll() {
		return mapper.toDto(repository.findAll());
	}

	@Override
	public ParameterDTO create(ParameterDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To create Parameter, 'dto' must not be null");
		}
		if (dto.getId() != null) {
			throw new RuntimeException("To create Parameter, 'id' must be null");
		}

		if (dto.getChildParams() != null) {
			for (int i = 0; i < dto.getChildParams().size(); i++) {
				dto.getChildParams().get(i).setType(dto.getType());
			}
		}

		return save(dto);
	}

	public ParameterDTO update(ParameterDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To update Parameter, 'dto' must not be null");
		}
		if (dto.getId() == null) {
			throw new RuntimeException("To update Parameter, 'id' must not be null");
		}
		if (dto.getType() == null) {
			throw new RuntimeException("To update Parameter, 'type' must not be null");
		}
		if (dto.getValue() == null) {
			throw new RuntimeException("To update Parameter, 'value' must not be null");
		}

		return save(dto);
	}

	@Override
	public Optional<ParameterDTO> get(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Parameter 'id' is not valid value: '" + id + "'");
		}

		return repository.findById(id).map(mapper::toDto);
	}

	@Override
	public void delete(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Parameter 'id' is not valid value: '" + id + "'");
		}

		repository.deleteById(id);
	}

	private ParameterDTO save(ParameterDTO dto) {
		Parameter doc = mapper.toDocument(dto);

		doc = repository.save(doc);
		return mapper.toDto(doc);
	}

	@Override
	public List<ParameterDTO> search(ParameterSearchDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To search Parameter, 'dto' must not be null");
		}

		List<Parameter> list = repository.findBySearchDto(dto);
		return mapper.toDto(list);
	}

}
