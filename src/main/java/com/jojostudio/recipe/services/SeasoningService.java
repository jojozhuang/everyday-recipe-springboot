package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.Seasoning;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.SeasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SeasoningService {
  @Autowired
  SeasoningRepository seasoningRepository;

  public List<Seasoning> findAll() {
    return seasoningRepository.findAll();
  }

  public Seasoning findById(long id) throws NotFoundException {
    Optional<Seasoning> seasoning = seasoningRepository.findById(id);

    if (seasoning.isEmpty()) {
      throw new NotFoundException();
    }
    return seasoning.get();
  }

  public Seasoning create(Seasoning seasoning) {
    seasoning.setId(0L);
    return seasoningRepository.save(seasoning);
  }

  public Seasoning update(Seasoning seasoning) {
    return seasoningRepository.save(seasoning);
  }

  public void delete(long id) {
    seasoningRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return seasoningRepository.existsById(id);
  }
}
