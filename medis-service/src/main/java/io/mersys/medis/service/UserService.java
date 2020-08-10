package io.mersys.medis.service;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import io.mersys.medis.documents.user.User;


public interface UserService {

    User create(User object);

    Optional<User> find(String id);

    UserDetails loadUserByUsername(String userName);

    List<User> findAll();

    User update(String id, User object);

    String delete(String id);

	User findByUsername(String userName);

	boolean checkByUserAndPassword(User user, String password);
    
    
}
