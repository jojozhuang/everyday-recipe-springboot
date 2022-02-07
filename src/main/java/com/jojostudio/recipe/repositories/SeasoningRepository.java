package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.Seasoning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasoningRepository extends JpaRepository<Seasoning, Long> { }
