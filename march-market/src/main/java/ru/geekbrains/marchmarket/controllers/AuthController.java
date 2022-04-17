package ru.geekbrains.marchmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.marchmarket.converters.UserConverter;
import ru.geekbrains.marchmarket.dtos.JwtRequest;
import ru.geekbrains.marchmarket.dtos.JwtResponse;
import ru.geekbrains.marchmarket.dtos.UserDto;
import ru.geekbrains.marchmarket.entities.User;
import ru.geekbrains.marchmarket.exceptions.AppError;
import ru.geekbrains.marchmarket.services.UserService;
import ru.geekbrains.marchmarket.utils.JwtTokenUtil;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/email")
    public String email(Principal principal){
        UserDto userDto = userConverter.entityToDto(userService.findByUsername(principal.getName()).get());
        return userDto.getEmail();
    }
}