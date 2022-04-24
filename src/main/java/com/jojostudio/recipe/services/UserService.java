package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.User;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(long id) throws NotFoundException {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new NotFoundException();
    }
    return user.get();
  }

  public User create(User user) {
    user.setId(0L);
    return userRepository.save(user);
  }

  public User update(User user) {
    return userRepository.save(user);
  }

  public void delete(long id) {
    userRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return userRepository.existsById(id);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public User signUp(User user) {
    user.setId(0L);
    return userRepository.save(user);
  }
}
