package com.werfen.laboratory.features.user.service;

import com.werfen.laboratory.features.user.model.User;
import com.werfen.laboratory.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUser(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User u) {
        u.setId(null);
        return this.userRepository.save(u);
    }

    public User updateUser(UUID id, User u) {
        User user = getUser(id);
        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setRole(u.getRole());
        return this.userRepository.save(user);
    }

    public User deleteUser(UUID id) {
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }
}