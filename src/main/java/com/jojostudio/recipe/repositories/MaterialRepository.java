package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> { }
