package com.book.ReadingIsGood.service;

import com.book.ReadingIsGood.enums.UserType;
import com.book.ReadingIsGood.jwt.JwtUtil;
import com.book.ReadingIsGood.model.entity.User;
import com.book.ReadingIsGood.dtos.requestDto.CreateUserDto;
import com.book.ReadingIsGood.dtos.requestDto.LoginRequestDto;
import com.book.ReadingIsGood.dtos.responseDto.UserInfoDto;
import com.book.ReadingIsGood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    final private PasswordEncoder encoder;

    final private AuthenticationManager authenticationManager;

    final private JwtUtil jwtUtils;

    //Todo dönüş hatalı ise exception fırlatılacak düzeltilecek
    public ResponseEntity userRegister( CreateUserDto createUserDto,UserType userType){

        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("E-mail already exist!!!");
        }
        User user = User.builder().name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .email(createUserDto.getEmail())
                .password(encoder.encode(createUserDto.getPassword()))
                .userType(userType).build();
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }


    public ResponseEntity authenticateUser(LoginRequestDto loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(UserInfoDto.builder().token(token).id(userDetails.getId()).role(roles.get(0)).email(userDetails.getUsername()).build());
    }
}
