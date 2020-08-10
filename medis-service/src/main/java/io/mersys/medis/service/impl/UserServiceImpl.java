package io.mersys.medis.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.mersys.medis.documents.user.User;
import io.mersys.medis.repository.UserRepository;
import io.mersys.medis.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private final UserRepository repository;

	private BCryptPasswordEncoder bcryptEncoder;

	public UserServiceImpl(final UserRepository repository, @Lazy final BCryptPasswordEncoder bcryptEncoder) {
		this.repository = repository;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username:" + username + " not found");
		} else if (!user.isAccountNonLocked()) {
			throw new UsernameNotFoundException("User with username:" + username + " is locked");
		}

		return user;
	}
	@Override
	public boolean checkByUserAndPassword(final User user, final String password) {
		if (user == null) {
			throw new UsernameNotFoundException("User is null");
		}
		if (password == null) {
			throw new UsernameNotFoundException("Empty Password");
		} else if (!bcryptEncoder.matches(password, user.getPassword())) {
			throw new UsernameNotFoundException("Password is incorrect");
		}
		return true;
	}

	@Override
	public User create(final User user) {
		user.setAccountNonLocked(true);
		user.setCreatedAt(String.valueOf(LocalDateTime.now()));
		user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public Optional<User> find(final String id) {
		return repository.findById(id);
	}

	@Override
	public User findByUsername(final String userName) {
		return repository.findByUsername(userName);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User update(final String id, final User user) {
		user.setId(id);

		final Optional<User> saved = repository.findById(id);

		if (saved.isPresent()) {
			user.setCreatedAt(saved.get().getCreatedAt());
			user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
		} else {
			user.setCreatedAt(String.valueOf(LocalDateTime.now()));
		}
		repository.save(user);
		return user;
	}

	@Override
	public String delete(final String id) {
		repository.deleteById(id);
		return id;
	}
}
