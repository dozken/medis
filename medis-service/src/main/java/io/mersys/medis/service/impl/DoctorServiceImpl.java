package io.mersys.medis.service.impl;

import io.mersys.medis.documents.doctor.Doctor;
import io.mersys.medis.repository.DoctorRepository;
import io.mersys.medis.service.DoctorService;
import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;
import io.mersys.medis.service.mapper.DoctorMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository repository;
    private DoctorMapper mapper;

    @Override
    public List<DoctorDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public Optional<DoctorDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Doctor 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);

    }

    @Override
    public DoctorDTO create(DoctorDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create Doctor, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create Doctor, 'id' must be null");
        }


        return save(dto);

    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Doctor 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);

    }

    @Override
    public DoctorDTO update(DoctorDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update Doctor, 'dto' must not be null");
        }
        if (dto.getId() == null) {
            throw new RuntimeException("To update Doctor, 'id' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update Doctor, 'name' must not be null");
        }

        return save(dto);
    }

    private DoctorDTO save(DoctorDTO dto) {
        Doctor doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public List<DoctorDTO> search(DoctorSearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search Doctor, 'dto' must not be null");
        }

        List<Doctor> list = repository.findBySearchDTO(dto);
        return mapper.toDto(list);

    }
}
