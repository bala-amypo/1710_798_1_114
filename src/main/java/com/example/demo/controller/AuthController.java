// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import com.example.demo.config.JwtTokenProvider;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {

//     private final UserService userService;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     public AuthController(UserService userService,
//                           JwtTokenProvider jwtTokenProvider) {
//         this.userService = userService;
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @PostMapping("/register")
//     @Operation(summary = "Register a new user")
//     public User register(@RequestBody RegisterRequest request) {
//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(passwordEncoder.encode(request.getPassword()));
//         return userService.register(user);
//     }

//     @PostMapping("/login")
//     @Operation(summary = "Login user and return JWT")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         User user = userService.findByEmail(request.getEmail());

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         String token = jwtTokenProvider.generateToken(
//                 user.getId(),
//                 user.getEmail(),
//                 user.getRole()
//         );

//         return new AuthResponse(token);
//     }
//}
