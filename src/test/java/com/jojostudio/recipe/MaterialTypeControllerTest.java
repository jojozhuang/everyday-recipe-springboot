package com.jojostudio.recipe;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.jojostudio.recipe.controllers.MaterialTypeController;
import com.jojostudio.recipe.entities.MaterialType;
import com.jojostudio.recipe.properties.UrlConfigProperties;
import com.jojostudio.recipe.services.MaterialTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@Disabled
@EnableConfigurationProperties(UrlConfigProperties.class)
@WebMvcTest(MaterialTypeController.class)
class MaterialTypeControllerTest extends BaseControllerTest {
  @MockBean
  MaterialTypeService mockService;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Disabled
  @Test
  public void testFindAll() throws Exception {
    when(mockService.findAll()).thenReturn(List.of(mockMaterialType1()));

    mockMvc.perform(get("/api/materialtypes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].typeName").isNotEmpty())
        .andExpect(jsonPath("$[0].typeName").value("Meat"));
  }

  @Disabled
  @Test
  public void testFindOne() throws Exception {
    when(mockService.exists(1)).thenReturn(true);
    when(mockService.findById(1)).thenReturn(mockMaterialType1());

    mockMvc.perform(get("/api/materialtypes/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.typeName").value("Meat"));
  }

  @Disabled
  @Test
  public void testCreate() throws Exception {
    when(mockService.create(any())).thenReturn(mockMaterialType1WithId());

    mockMvc.perform(post("/api/materialtypes")
        .content(asJsonString(mockMaterialType1()))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());
  }

  @Disabled
  @Test
  public void testUpdate() throws Exception {
    MaterialType materialType1 = mockMaterialType1WithId();
    MaterialType materialType3 = mockMaterialType2WithId();
    materialType3.setId(1L);
    when(mockService.exists(1)).thenReturn(true);
    when(mockService.findById(1)).thenReturn(materialType1);
    when(mockService.update(any())).thenReturn(materialType3);

    MaterialType materialType2 = mockMaterialType2();
    mockMvc.perform(put("/api/materialtypes/1")
        .content(asJsonString(materialType2))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(materialType3.getId()))
        .andExpect(jsonPath("$.typeName").value(materialType3.getTypeName()));
  }

  @Disabled
  @Test
  public void testDelete() throws Exception {
    when(mockService.exists(1)).thenReturn(true);

    mockMvc.perform(delete("/api/materialtypes/1"))
        .andExpect(status().isOk());
  }
}