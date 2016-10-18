package com.simscale.catalog.rest.service.impl;

import com.simscale.catalog.rest.domain.User;
import com.simscale.catalog.rest.domain.UserRepository;
import com.simscale.catalog.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Optional<User> findByIdOptional(Long id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public void update(User originalUser, User updatedUser) {
        originalUser.setFirstName(updatedUser.getFirstName());
        originalUser.setLastName(updatedUser.getLastName());
        originalUser.setUsername(updatedUser.getUsername());
        userRepository.save(originalUser);
    }

    @Override
    public void create(User user) {
        //validade
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
