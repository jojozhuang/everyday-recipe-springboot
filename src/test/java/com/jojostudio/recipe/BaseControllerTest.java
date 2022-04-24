package com.jojostudio.recipe;

import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojostudio.recipe.entities.MaterialType;

public class BaseControllerTest {
  protected String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected MaterialType mockMaterialType1() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Meat");
    return mockMaterialType;
  }

  protected MaterialType mockMaterialType1WithId() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setId(1L);
    mockMaterialType.setTypeName("Meat");
    return mockMaterialType;
  }

  protected MaterialType mockMaterialType2() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Vegetable");
    return mockMaterialType;
  }

  protected MaterialType mockMaterialType2WithId() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setId(2L);
    mockMaterialType.setTypeName("Vegetable");
    return mockMaterialType;
  }

  protected MaterialType mockMaterialType3() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setTypeName("Fruit");
    return mockMaterialType;
  }

  protected MaterialType mockMaterialType3WithId() {
    MaterialType mockMaterialType = new MaterialType();
    mockMaterialType.setId(3L);
    mockMaterialType.setTypeName("Fruit");
    return mockMaterialType;
  }

  protected void assertMaterialType(MaterialType actual, MaterialType expected) {
    assertThat(actual).isNotNull();
    assertThat(actual.getTypeName()).isEqualTo(expected.getTypeName());
  }
}
