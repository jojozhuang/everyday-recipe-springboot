package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.Role;
import com.jojostudio.recipe.models.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRoleType(RoleType roleType);
}
