package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.Material;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
  @Autowired
  MaterialRepository materialRepository;

  public List<Material> findAll() {
    return materialRepository.findAll();
  }

  public Material findById(long id) throws NotFoundException {
    Optional<Material> material = materialRepository.findById(id);

    if (material.isEmpty()) {
      throw new NotFoundException();
    }
    return material.get();
  }

  public Material create(Material material) {
    material.setId(0L);
    return materialRepository.save(material);
  }

  public Material update(Material material) {
    return materialRepository.save(material);
  }

  public void delete(long id) {
    materialRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return materialRepository.existsById(id);
  }
}
