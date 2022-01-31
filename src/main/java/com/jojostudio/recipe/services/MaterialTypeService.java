package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.MaterialType;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.MaterialTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialTypeService {
  @Autowired
  MaterialTypeRepository materialTypeRepository;

  public List<MaterialType> findAll() {
    return materialTypeRepository.findAll();
  }

  public MaterialType findById(long id) throws NotFoundException {
    Optional<MaterialType> materialType = materialTypeRepository.findById(id);

    if (materialType.isEmpty()) {
      throw new NotFoundException();
    }
    return materialType.get();
  }

  public MaterialType create(MaterialType materialType) {
    materialType.setId(0L);
    return materialTypeRepository.save(materialType);
  }

  public MaterialType update(MaterialType materialType) {
    return materialTypeRepository.save(materialType);
  }

  public void delete(long id) {
    materialTypeRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return materialTypeRepository.existsById(id);
  }
}
