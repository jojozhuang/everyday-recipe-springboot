package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> { }
