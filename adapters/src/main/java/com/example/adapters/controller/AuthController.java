package com.example.adapters.controller;


import com.example.adapters.entity.LoginRequestDTO;
import com.example.adapters.entity.LoginResponseDTO;
import com.example.adapters.entity.RegisterRequestDTO;
import com.example.adapters.security.JWTGenerator;
import com.example.domain.port.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(generator.generateToken(authentication));
            return ResponseEntity.ok((loginResponseDTO));
            //return ResponseEntity.ok(LoginResponseDTO.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Propriété isAdmin est mise en "false" par défaut, car cette méthode sera appelée lorsque l'utilisateur s'inscrit par lui même
    //Pour créer un admin il faut passer par la méthode "create" dans "UserController"
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            registerRequestDTO.setAdmin(false);
            userService.createUser(
                    registerRequestDTO.getFirstName(),
                    registerRequestDTO.getLastName(),
                    registerRequestDTO.getPhone(),
                    registerRequestDTO.getEmail(),
                    passwordEncoder.encode(registerRequestDTO.getPassword()),
                    registerRequestDTO.isAdmin(),
                    registerRequestDTO.getImageUrl()
            );
            return ResponseEntity.ok("Inscription effectuée");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
