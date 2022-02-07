package com.jojostudio.recipe.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private Long id;
  private String userName;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String userName, String email, List<String> roles) {
    this.accessToken = accessToken;
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.roles = roles;
  }
}
