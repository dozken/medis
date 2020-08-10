package io.mersys.medis.web.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.medis.service.UserService;
import io.mersys.medis.service.dto.UserDTO;
import io.mersys.medis.service.mapper.UserMapper;


@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final UserService service;

    private final UserMapper mapper;

    @Autowired
    public SignUpController(final UserService service,
                            final UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody final UserDTO dto) {
        return new ResponseEntity<>(service.create(mapper.toDocument(dto)), HttpStatus.OK);
    }
}
