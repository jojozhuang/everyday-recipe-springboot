package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.Role;
import com.jojostudio.recipe.entities.User;
import com.jojostudio.recipe.models.RoleType;
import com.jojostudio.recipe.payload.request.LoginDTO;
import com.jojostudio.recipe.payload.request.SignUpDTO;
import com.jojostudio.recipe.payload.response.JwtResponse;
import com.jojostudio.recipe.payload.response.MessageResponse;
import com.jojostudio.recipe.repositories.RoleRepository;
import com.jojostudio.recipe.repositories.UserRepository;
import com.jojostudio.recipe.security.jwt.JwtUtils;
import com.jojostudio.recipe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = AuthController.MAX_AGE)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public static final int MAX_AGE = 3600;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpDTO) {
    if (userRepository.existsByUsername(signUpDTO.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpDTO.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
    }

    Role userRole = roleRepository.findByRoleType(RoleType.ROLE_STARTER)
        .orElseThrow(() -> new RuntimeException("Error: Role STARTER is not found."));

    // Create new user's account
    User user = new User(signUpDTO.getUsername(), signUpDTO.getEmail(), encoder.encode(signUpDTO.getPassword()));
    user.setRoles(Set.of(userRole));
    userRepository.save(user);
    /*
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }*/

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
