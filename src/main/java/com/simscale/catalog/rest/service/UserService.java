package com.simscale.catalog.rest.service;

import com.simscale.catalog.rest.domain.User;

import java.util.Optional;

public interface UserService {

    public User findById(Long id);

    public Optional<User> findByIdOptional(Long id);

    public void update(User originalUser, User updatedUser);

    public void create(User user);

    public void delete(Long id);

    public void delete(User user);

    public Iterable<User> findAll();

}
