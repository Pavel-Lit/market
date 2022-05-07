package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.api.JwtRequest;
import ru.geekbrains.march.market.api.JwtResponse;
import ru.geekbrains.march.market.api.UserDto;
import ru.geekbrains.march.market.core.converters.UserConverter;
import ru.geekbrains.march.market.core.exceptions.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.core.services.UserService;
import ru.geekbrains.march.market.core.utils.JwtTokenUtil;

import java.security.Principal;

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
    @GetMapping("/about")
    public UserDto aboutCurrentUser(Principal principal){
        UserDto userDto = userConverter.entityToDto(userService.findByUsername(principal.getName()).get());
        return userDto;
    }
}