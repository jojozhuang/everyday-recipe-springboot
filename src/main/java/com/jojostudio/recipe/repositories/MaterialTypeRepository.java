package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Long> { }
