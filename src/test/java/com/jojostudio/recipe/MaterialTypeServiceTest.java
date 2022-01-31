package com.jojostudio.recipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.jojostudio.recipe.entities.MaterialType;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.MaterialTypeRepository;
import com.jojostudio.recipe.services.MaterialTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MaterialTypeServiceTest {
  @InjectMocks
  MaterialTypeService service;

  @Mock
  MaterialTypeRepository repository;

  @Test
  public void testFindAll() {
    MaterialType mockMaterialType1 = new MaterialType();
    mockMaterialType1.setTypeName("Meat");
    MaterialType mockMaterialType2 = new MaterialType();
    mockMaterialType2.setTypeName("Vegetable");

    when(repository.findAll()).thenReturn(List.of(mockMaterialType1, mockMaterialType2));
    List<MaterialType> materialTypes = service.findAll();

    assertThat(materialTypes.size()).isEqualTo(2);
    assertThat(materialTypes.get(0)).isEqualTo(mockMaterialType1);
    assertThat(materialTypes.get(1)).isEqualTo(mockMaterialType2);
    verify(repository).findAll();
  }

  @Test
    public void testFindById() throws Exception {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Meat");

    when(repository.findById(1L)).thenReturn(Optional.of(mockMaterialType));
    MaterialType materialType = service.findById(1);

    assertThat(materialType).isEqualTo(mockMaterialType);
    verify(repository).findById(1L);
  }

  @Test
  public void testFindByIdNotFound() {
    Optional<MaterialType> mockMaterialType = Optional.empty();

    when(repository.findById(1L)).thenReturn(mockMaterialType);

    assertThrows(NotFoundException.class, () -> {
      service.findById(1);
    });
    verify(repository).findById(1L);
  }

  @Test
  public void testCreate() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Meat");

    when(repository.save(any())).thenReturn(mockMaterialType);
    MaterialType materialType = service.create(mockMaterialType);

    assertThat(materialType).isEqualTo(mockMaterialType);
    verify(repository).save(any());
  }

  @Test
  public void testUpdate() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Meat");

    when(repository.save(any())).thenReturn(mockMaterialType);
    MaterialType materialType = service.update(mockMaterialType);

    assertThat(materialType).isEqualTo(mockMaterialType);
    verify(repository).save(any());
  }

  @Test
  public void testDelete() {
    service.delete(1L);

    verify(repository).deleteById(1L);
  }

  @Test
  public void testExists() {
    when(repository.existsById(1L)).thenReturn(true);
    boolean exists = service.exists(1);

    assertThat(exists).isEqualTo(true);
    verify(repository).existsById(1L);
  }
}
